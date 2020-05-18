package com.lem.baseapp.log;

import com.lem.baseapp.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;

/**
 * 日志书写类
 *
 * @author LiuShuai
 */
public class LogCacheFile {
    private static final String DEBUG_FILE_NAME = "debug_cache.log";
    private static final String ERROR_FILE_NAME = "error_cache.log";
    private static final String HTTP_FILE_NAME = "http_cache.log";

    private static final String DEBUG_LOG_WRAPPER
            = "[%s] debug log -->%s\n ";
    private static final String ERROR_LOG_WRAPPER
            = "[%s] error log -->%s\n";

    private File mDebugCacheFile;
    private File mErrorCacheFile;
    private File mHttpCacheFile;
    private static LogCacheFile sInstance;

    public static LogCacheFile getInstance() throws IOException {
        if (sInstance == null) {
            sInstance = new LogCacheFile();
        }
        return sInstance;
    }

    private LogCacheFile() throws IOException {
        mDebugCacheFile =
                getInitFile(
                        BaseApplication.getInstance().getExternalCacheDir() + File.separator + DEBUG_FILE_NAME);
        mErrorCacheFile =
                getInitFile(
                        BaseApplication.getInstance().getExternalCacheDir() + File.separator + ERROR_FILE_NAME);
        mHttpCacheFile =
                getInitFile(
                        BaseApplication.getInstance().getExternalCacheDir() + File.separator + HTTP_FILE_NAME);
    }

    public void logD(String log) throws IOException {
        writeLog2File(mDebugCacheFile, String.format(DEBUG_LOG_WRAPPER, new Date(), log));
    }

    public void logE(String log) throws IOException {
        writeLog2File(mErrorCacheFile, String.format(ERROR_LOG_WRAPPER, new Date(), log));
    }

    public void logHttp(String log) throws IOException {
        writeLog2File(mHttpCacheFile, log);
    }

    public void cleanCacheFile() {
        if (mErrorCacheFile.exists()) {
            mErrorCacheFile.delete();
        }
        if (mDebugCacheFile.exists()) {
            mDebugCacheFile.delete();
        }
        if (mHttpCacheFile.exists()) {
            mHttpCacheFile.delete();
        }
    }

    public Completable rxCleanCacheFile() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                cleanCacheFile();
                emitter.onComplete();
            }
        });
    }

    public Completable rxLogD(final String log) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                logD(log);
                emitter.onComplete();
            }
        });
    }

    public Completable rxLogE(final String log) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                logE(log);
                emitter.onComplete();
            }
        });
    }

    public Completable rxLogHttp(final String log) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                logHttp(log);
                emitter.onComplete();
            }
        });
    }

    private File getInitFile(String path) throws IOException {
        File file = new File(path);
        if (file.isDirectory()) {
            throw new FileNotFoundException(path + " file is directory!");
        }
        if (file.exists()) {
            return file;
        } else {
            if (file.createNewFile()) {
                return file;
            } else {
                throw new IOException("create file failure!");
            }
        }
    }

    private void writeLog2File(File file, String log) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, true);
        PrintWriter printWriter = new PrintWriter(fos);
        printWriter.println(log);
        printWriter.flush();
        printWriter.close();
        fos.close();
    }
}
