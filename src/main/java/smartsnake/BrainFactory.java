package smartsnake;

/**
 * Loads the class that should be used as the Brain
 */
public class BrainFactory {

    /**
     * @return Brain class specified by the "brainClassName" environment variable.
     * @throws IllegalStateException if an error occurs
     */
    public Brain newInstance() throws IllegalStateException {
        String brainClassName = System.getenv("brainClassName");
        try {
            Class<?> cls = Class.forName(brainClassName);
            return (Brain) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to instantiate class " + brainClassName, e);
        }
    }

}
