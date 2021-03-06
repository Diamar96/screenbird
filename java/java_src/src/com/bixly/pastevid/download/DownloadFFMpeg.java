/*
 * DownloadFFMpeg.java
 *
 * Version 1.0
 * 
 * 4 May 2013
 */
package com.bixly.pastevid.download;

import com.bixly.pastevid.Settings;
import com.bixly.pastevid.util.LibraryUtil;
import com.bixly.pastevid.util.MediaUtil;
import java.io.File;

/**
 * Extension of DownloadThread class for downloading the FFMpeg library.
 * @author cevaris
 */
public class DownloadFFMpeg  extends DownloadThread {
    // FFMpeg executable file
    private final File executable = new File(Settings.getFFMpegExecutable());
    
    public DownloadFFMpeg() {
        super("FFMpeg Download");
        this.file = new File(Settings.BIN_DIR , "ffmpeg.zip");
        this.url  = Settings.getFFMpegLibURL();
    }

    @Override
    protected void preDownloadProcedure() {
        // Do nothing
    }

    @Override
    protected void postDownloadProcedure() {
        // Unzip compressed FFMpeg            
        LibraryUtil.unzip(this.file.getAbsolutePath());
        
        // Make FFMpeg executable     
        if (MediaUtil.osIsWindows()) {
            this.executable.setExecutable(true);    // Windows
        } else {
            LibraryUtil.chmod("+x", executable);    // Mac/Linux
        }
        
        // Overwrite file so it can be reference executable
        this.file = executable;
    }
}
