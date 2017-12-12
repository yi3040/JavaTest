package com.example.demo.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ywf on 17-12-6.
 */
public class BeanUtil {
    public static final String[] RETURN_TYPES = new String[]{"java.lang.String", "java.util.Date", "java.lang.Boolean", "java.lang.Character", "java.lang.Byte", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double", "boolean", "char", "byte", "short", "int", "long", "float", "double"};
    public static final String[] RETURN_COLLS_TYPES = new String[]{"java.util.Set", "java.util.List"};
    private static final BeanUtil bu = new BeanUtil();
    private Map<String, List<Method>> sourCollMethods = new HashMap();
    private Map<String, BeanUtil.CollGetSetMethod> sourReturnValueCollectionObjectMethods = new HashMap();
    private Map<String, List<Method>> sourMethods = new HashMap();
    private Map<String, Map<String, Method>> destMethods = new HashMap();

    private BeanUtil() {
    }

    public static BeanUtil getInstance() {
        return bu;
    }

    private Map<String, Method> getDestMethodMap(Object dest) {
        String destName = dest.getClass().getName();
        if (!this.destMethods.containsKey(destName)) {
            this.destMethods.put(destName, this.getSetMethods(dest.getClass()));
        }

        return (Map) this.destMethods.get(destName);
    }


    private static boolean contains(String returnType) {
        if (returnType.contains("interface")) {
            return false;
        } else {
            String[] var1 = RETURN_TYPES;
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                String s = var1[var3];
                if (returnType.contains(s)) {
                    return true;
                }
            }

            return false;
        }
    }

    private static boolean containsColls(String returnType) {
        String[] var1 = RETURN_COLLS_TYPES;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (returnType.contains(s)) {
                return true;
            }
        }

        return false;
    }

    private Map<String, Method> getSetMethods(Class clazz) {
        Method[] ms = clazz.getMethods();
        Map<String, Method> map = new HashMap();
        if (ms == null) {
            return map;
        } else {
            Method[] var4 = ms;
            int var5 = ms.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Method m = var4[var6];
                if (m != null && m.getName().startsWith("set") && m.getReturnType().toString().equals("void")) {
                    map.put(m.getName(), m);
                }
            }

            return map;
        }
    }

    private List<Method> getGetMethodsColl(Object obj) {
        return this.getGetMethodsColl(obj.getClass());
    }

    private String buildKey(String mainClassName, String subClassName) {
        return mainClassName + "_" + subClassName;
    }


    private List<Method> getGetMethods(Object obj) {
        return this.getGetMethods(obj.getClass());
    }

    private List<Method> getGetMethods(Class clazz) {
        return this.getGetMethodsCommon(clazz, new BeanUtil.OperMethod() {
            public boolean operMethod(Method m) {
                return BeanUtil.contains(m.getReturnType().toString());
            }
        });
    }

    private List<Method> getGetMethodsCommon(Class clazz, BeanUtil.OperMethod om) {
        Method[] ms = clazz.getMethods();
        List<Method> list = new ArrayList();
        if (ms == null) {
            return list;
        } else {
            Method[] var5 = ms;
            int var6 = ms.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                Method m = var5[var7];
                if (m != null && m.getName().startsWith("get") && om.operMethod(m)) {
                    list.add(m);
                }
            }

            return list;
        }
    }

    public List<Method> getSetMethodsObject(Object o) {
        Method[] ms = o.getClass().getMethods();
        List<Method> list = new ArrayList();
        if (ms == null) {
            return list;
        } else {
            Method[] var4 = ms;
            int var5 = ms.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Method m = var4[var6];
                if (m != null && m.getName().startsWith("set") && !contains(m.getParameterTypes()[0].toString())) {
                    list.add(m);
                }
            }

            return list;
        }
    }

    public static String toObjectString(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            Class clazz = obj.getClass();
            Method[] methods = clazz.getMethods();
            if (methods != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(clazz.getName());
                sb.append("  [");
                Method[] var4 = methods;
                int var5 = methods.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    Method m = var4[var6];
                    if (m != null) {
                        String name = m.getName();
                        if (name.startsWith("get") && !"getClass".equals(name)) {
                            String propertyName = name.substring(3);
                            String first = propertyName.substring(0, 1);
                            first = first.toLowerCase();
                            propertyName = first + propertyName.substring(1);

                            try {
                                sb.append(propertyName);
                                sb.append(":");
                                Object o = m.invoke(obj, (Object[]) null);
                                if (o != null) {
                                    sb.append(o.toString());
                                } else {
                                    sb.append("null");
                                }

                                sb.append(",");
                            } catch (IllegalAccessException var12) {
                                var12.printStackTrace();
                            } catch (InvocationTargetException var13) {
                                var13.printStackTrace();
                            }
                        }
                    }
                }

                String s = sb.toString();
                if (s.endsWith(",")) {
                    s = s.substring(0, s.length() - 1);
                }

                s = s + "]";
                return s;
            } else {
                return null;
            }
        }
    }

    private interface OperMethod {
        boolean operMethod(Method var1);
    }

    private class CollGetSetMethod {
        private Method getMethod;
        private Method setMethod;

        private CollGetSetMethod() {
        }

        public Method getGetMethod() {
            return this.getMethod;
        }

        public void setGetMethod(Method getMethod) {
            this.getMethod = getMethod;
        }

        public Method getSetMethod() {
            return this.setMethod;
        }

        public void setSetMethod(Method setMethod) {
            this.setMethod = setMethod;
        }
    }

    private static enum GetMethodInvokeType {
        baseType,
        collection;

        private GetMethodInvokeType() {
        }
    }

    private static enum CopyType {
        formToModel,
        modelToView;

        private CopyType() {
        }
    }
}
