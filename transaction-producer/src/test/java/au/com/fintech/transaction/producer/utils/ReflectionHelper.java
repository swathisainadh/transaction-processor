package au.com.fintech.transaction.producer.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionHelper {

    private static final String MODIFIERS = "modifiers";

    /**
     * Modifies constant's value (generally it is "public static final .." )
     *
     * @param clazz
     * @param fieldName
     * @param fieldValue
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setStaticFinalField(final Class clazz, final String fieldName, final Object fieldValue) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class clazzInHierarchy = clazz;
        Field field = null;
        while (clazzInHierarchy != null && field == null) {
            try {
                field = clazzInHierarchy.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazzInHierarchy = clazzInHierarchy.getSuperclass();
            }
        }
        if (field != null) {
            field.setAccessible(true);
            final Field modifiersField = Field.class.getDeclaredField(MODIFIERS);
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, fieldValue);
        } else {
            throw new NoSuchFieldException("The class " + clazz.getName() + " and its superclasses do not have field '" + fieldName + "'");
        }
    }
}
