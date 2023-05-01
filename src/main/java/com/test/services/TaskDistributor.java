package com.test.services;

import java.io.IOException;

public interface TaskDistributor {

    void run(Boolean runProject) throws IOException;
}
