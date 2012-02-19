package com.tc.webatm.db;

import java.lang.annotation.*;

public interface ExecutionMarkers {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface FetchMethod { }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AmendMethod { }
}
