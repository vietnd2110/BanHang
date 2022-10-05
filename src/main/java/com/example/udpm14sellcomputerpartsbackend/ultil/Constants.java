package com.example.udpm14sellcomputerpartsbackend.ultil;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static class RESPONSE_TYPE {
        public static final String SUCCESS = "SUCCESS";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String CONFIRM = "CONFIRM";
        public static final String invalidPermission = "invalidPermission";
    }

    public static class RESPONSE_CODE {
        public static final String SUCCESS = "success";
        public static final String DELETE_SUCCESS = "deleteSuccess";
        public static final String ERROR = "error";
        public static final String WARNING = "warning";
        public static final String RECORD_DELETED = "record.deleted";
    }

    public static class AUTHEN_INFO {
        public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
        public static final String SIGNING_KEY = "devglan123r";
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final String AUTHORITIES_KEY = "scopes";
    }

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";


    // Cấp bậc menu
    public static class LEVEL {
        public static final Integer ONE = 1;
        public static final Integer TWO = 2;

        private LEVEL() {
        }
    }

    // Trạng thái
    public static class ACTIVE {
        public static final Integer IS_ACTIVE = 1;
        public static final Integer NOT_ACTICE = 0;

        private ACTIVE() {
        }
    }

    // Tạo lớp hằng kiểm tra đã support chưa
    public static class SUPPORT {
        public static final Integer IS_NOT_SUPPORTED = 0;
        public static final Integer IS_SUPPORTED = 1;

        private SUPPORT() {
        }
    }

    public static List<String> LIST_IMAGE_FILE_ACCEPT = Arrays.asList("PND", "JPG", "JPEG");
}
