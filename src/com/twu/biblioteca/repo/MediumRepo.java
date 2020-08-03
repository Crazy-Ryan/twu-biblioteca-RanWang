package com.twu.biblioteca.repo;

import java.util.List;

public interface MediumRepo <T> {

    List<T> getMedia();

    List<T> getAvailableMedia();

    boolean checkoutMedium(String name);

    boolean returnMedium(String name);

}
