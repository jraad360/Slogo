package backend_internal;


/**
 * Constant represents a number. Implements the InputType interface.
 * @author Jamie Palka and Jorge Raad
 */
public class Constant implements InputType {
    private double myNumber;
    private static final int PARAM_NUM = 0;

    public Constant(){ }

    public Constant(double number){
        myNumber = number;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    @Override
    public void setParameter(InputType parameter) {
        myNumber = parameter.execute();
    }

    /**
     * Sets the Constant's value to the given number
     * @param parameter - (String) String containing value of Constant
     */
    @Override
    public void setParameter(String parameter) {
        myNumber = Double.parseDouble(parameter);
    }

    /**
     * Returns number stored within Constant
     * @return - (double) value of Constant
     */
    @Override
    public double execute() {
        return myNumber;
    }

}

