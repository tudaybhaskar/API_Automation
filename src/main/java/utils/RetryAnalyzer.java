package utils;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryAnalyzer implements IAnnotationTransformer
{

    public void transform(
            ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        int retryCount = 2;
        boolean shouldRetry = true;
        if (shouldRetry) {
            annotation.setRetryAnalyzer(FailRetry.class);
        }
    }

}
