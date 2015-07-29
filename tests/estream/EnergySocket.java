package estream;

modes { low <: mid; mid <: high; }

/*
 * Good example of wanting to use our class mode type variables
 * in our constructor.
public class EnergySocket@mode<X, Y<=X> {
  private BufferedEnergyOutputStream@mode<X,Y> stream;

  public EnergySocket(String host, int port) {
    this.stream = new BufferedEnergyOutputStream@mode<X,Y>();
  }

  public EnergyOutputStream@mode<X,Y> outputStream() {
    return this.stream;
  }
}
*/

public class EnergySocket@mode<S, T<=S> {
  private BufferedEnergyOutputStream@mode<S,T> stream;

  public EnergySocket(String host, int port) {
    this.stream = new BufferedEnergyOutputStream@mode<S,T>();
  }

  public EnergyOutputStream@mode<S,T> outputStream() {
    return this.stream;
  }
}

