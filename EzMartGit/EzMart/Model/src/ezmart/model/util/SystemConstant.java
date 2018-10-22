package ezmart.model.util;

public final class SystemConstant {

    public class PAGE {

        public class SIZE {

            public final static String LIMIT = "25";

        }

    }

    public class SQL {

    }

    public class EMAIL {

        public class AUTHENTICATION {

            public final static String CONFIRMATION_REGISTER = "CONFIRMATION_REGISTER";
            public final static String RECOVERY_PASSWORD = "RECOVERY_PASSWORD";

        }
    }

    public class VALIDATION {

        public class USER {

            public final static String AUTHENTICATION = "AUTHENTICATION";
            public final static String RECOVERY_PASSWORD = "RECOVERY_PASSWORD";

        }

        public class REGISTER {

            public final static String REGISTER_CONSUMER = "REGISTER_CONSUMER";
            public final static String REGISTER_ESTABLISHMENT = "REGISTER_ESTABLISHMENT";

        }

        public class PRODUCT {

            public final static String ADD_PRODUCT_LIST = "ADD_PRODUCT_LIST";
            public final static String REGISTER_ESTABLISHMENT = "REGISTER_ESTABLISHMENT";

        }
    }

    public class USER {

        public class TYPE {

            public final static String ESTABLISHMENT = "emporium";
            public final static String CONSUMER = "consumer";

        }
    }
}
