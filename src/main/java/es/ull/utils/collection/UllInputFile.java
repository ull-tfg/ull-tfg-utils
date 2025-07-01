package es.ull.utils.collection;

import java.io.File;

/**
 *
 */
public interface UllInputFile {

    /**
     *
     * @param file
     */
    public abstract void setInputFile(File file);

    /**
     *
     * @return
     */
    public abstract File getInputFile();
}
