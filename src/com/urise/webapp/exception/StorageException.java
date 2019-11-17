package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class StorageException extends RuntimeException {
    private final Resume resume;

    public StorageException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public Resume getResume() {
        return resume;
    }
}
