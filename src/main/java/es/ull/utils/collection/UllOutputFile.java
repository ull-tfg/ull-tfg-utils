package es.ull.utils.collection;

import java.io.File;

/**
 *
 */
public interface UllOutputFile {

    /**
     *
     * @return
     */
    public abstract File getOutputFile();

    /**
     *
     * @param outputFile
     */
    public abstract void setOutputFile(File outputFile);
}
