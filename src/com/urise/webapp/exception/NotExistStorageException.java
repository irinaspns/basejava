package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(Resume resume) {
        super("Resume not exist.", resume);
    }
}
