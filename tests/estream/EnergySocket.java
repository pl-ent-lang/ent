package estream;

modes { low <: mid; mid <: high; }

public class EnergySocket@mode<X, Y<=X> {
  private BufferedEnergyOutputStream@mode<X,Y> stream;

  public EnergySocket(String host, int port) {
    this.stream = new BufferedEnergyOutputStream@mode<X,Y>();
  }

  public EnergyOutputStream@mode<X,Y> outputStream() {
    return this.stream;
  }

}
