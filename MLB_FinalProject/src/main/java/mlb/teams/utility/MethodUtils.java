package mlb.teams.utility;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

public class MethodUtils {

	private static final Predicate<Object> SKIP_NULL = v -> v == null;
	
	private static final Predicate<Object> SKIP_NULL_OR_ZERO = v ->
	    v == null ||
	    (v instanceof Number && ((Number) v).doubleValue() == 0.0) ||
	    (v instanceof BigDecimal && ((BigDecimal) v).compareTo(BigDecimal.ZERO) == 0) ||
	    (v instanceof String && ((String) v).trim().isEmpty());

	private static <T, V> void copyIfValid(
	            T source,
	            T target,
	            Function<T, V> getter,
	            BiConsumer<T, V> setter,
	            Predicate<? super V> skipCondition
	    ) {
	        V value = getter.apply(source);
	        if (value == null || skipCondition.test(value)) return;
	        setter.accept(target, value);
	    }
	
	public static <T, V> void copyIfNotNull(T source, T target, Function<T, V> getter, BiConsumer<T, V> setter) {
		copyIfValid(source, target, getter, setter, SKIP_NULL);
	}
	
	public static <T, V> void copyIfNotNullOrZero(T source, T target, Function<T, V> getter, BiConsumer<T, V> setter) {
		copyIfValid(source, target, getter, setter, SKIP_NULL_OR_ZERO);
	}
	
	public static <T> T findById(JpaRepository<T, String> repo, String repoId, String entityName) {
		return repo.findById(repoId).orElseThrow(() -> new NoSuchElementException(
				entityName + "with ID= " + repoId + " does not exist in the database."));
	}
	
	public static <T> T findById(JpaRepository<T, Long> repo, Long repoId, String entityName) {
		return repo.findById(repoId).orElseThrow(() -> new NoSuchElementException(
				entityName + "with ID= " + repoId + " does not exist in the database."));
	}
	
	private static <T> T findById(JpaRepository<T, Long> repo, Long repoId) {
		return repo.findById(repoId).orElseThrow(() -> new NoSuchElementException());
	}
	
	public static <T> T findOrCreateNew(JpaRepository<T, String> repo, String repoId, Supplier<T> constructor) {
		try {
			return findById(repo, repoId, "MLB");
		} catch (Exception e) {
			return constructor.get();
		}
	}
	
	public static <T> T findOrCreateNew(JpaRepository<T, Long> repo, Long repoId, Supplier<T> constructor) {
		if (Objects.isNull(repoId)) {
			return constructor.get();
		}else {
			return findById(repo, repoId);
		}
	}
}
