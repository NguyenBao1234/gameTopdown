//Copyright POWGameStd
package HelpDevGameTool;

public class Logger
{
    public static void logError(String message, Exception e)
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTracer = stackTrace[3]; // vi tri goi ham
        String location = String.format("at %s.%s(%s:%d)",
                stackTracer.getClassName(),
                stackTracer.getMethodName(),
                stackTracer.getFileName(),
                stackTracer.getLineNumber());
        System.err.println(message + location);
        if (e != null) {
            e.printStackTrace(System.err);
        }
    }
}
