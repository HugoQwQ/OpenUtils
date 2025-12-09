package org.afterlike.openutils.util.lang;

import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReflectionUtil {
	@SafeVarargs
	public static <Return, Owner> @Nullable Return getField(final @Nullable Owner object,
			final @NotNull String fieldName, final @NotNull Owner @NotNull... mock) {
		try {
			final @Nullable Class<?> clazz = object == null
					? mock.getClass().getComponentType()
					: object.getClass();
			if (clazz == null) {
				throw new ClassNotFoundException();
			}
			@Nullable Field field;
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (final NoSuchFieldException e) {
				try {
					field = clazz.getField(fieldName);
				} catch (final NoSuchFieldException ex) {
					field = null;
				}
			}
			if (field == null) {
				throw new NoSuchFieldException();
			}
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			// noinspection unchecked
			return (Return) field.get(object);
		} catch (final @NotNull Exception caught) {
			throw new RuntimeException("Failed to get field: " + fieldName, caught);
		}
	}

	@SafeVarargs
	public static <Value, Owner> void setField(final @Nullable Owner object,
			final @NotNull String fieldName, final @NotNull Value value,
			final @NotNull Owner @NotNull... mock) {
		try {
			final @Nullable Class<?> clazz = object == null
					? mock.getClass().getComponentType()
					: object.getClass();
			if (clazz == null) {
				throw new ClassNotFoundException();
			}
			@Nullable Field field;
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (final NoSuchFieldException e) {
				try {
					field = clazz.getField(fieldName);
				} catch (final NoSuchFieldException ex) {
					field = null;
				}
			}
			if (field == null) {
				throw new NoSuchFieldException();
			}
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			field.set(object, value);
		} catch (final @NotNull Exception caught) {
			throw new RuntimeException("Failed to set field: " + fieldName, caught);
		}
	}
}
