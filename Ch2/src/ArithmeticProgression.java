public class ArithmeticProgression extends Progression{
    protected long increment;

    public ArithmeticProgression(long stepsize) {
        this(stepsize, 0);
    }

    public ArithmeticProgression() {
        this(1,0);
    }

    public ArithmeticProgression(long stepsize, long start) {
        super(start);
        increment = stepsize;
    }

    public void advance() {
        current += increment;
    }
}
