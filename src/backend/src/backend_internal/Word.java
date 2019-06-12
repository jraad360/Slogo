package backend_internal;

public class Word implements InputType {
    private static final int PARAM_NUM = 0;
    private String myContents;

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    @Override
    public void setParameter(InputType parameter) {
    }

    /**
     * {@inheritDoc}
     * Sets the Word's contents to the given input.
     * @param parameter - desired Word contents
     */
    @Override
    public void setParameter(String parameter) {
        myContents = parameter;
    }

    @Override
    public double execute() throws IllegalStateException {
        return 0;
    }

    public String getString(){
        return myContents;
    }
}
