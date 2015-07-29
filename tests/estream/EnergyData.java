package estream;

import java.util.ArrayList;
import java.util.List;

public class EnergyData {
  private List<Byte> bytes;

  private mcase<float> compressionRatio = mcase<float>{
    low: 1.0f;
    mid: 3.0f;
    high: 5.0f;
  };

  attribute {
    int size = this.bytes.size();
    // Simulate compression
    if (size < 1024) {
      // Under 1KB, low energy
      return @mode<low>;
    } else if (size >= 1024 && size <= 524288) {
      // Under 512KB, mid energy
      return @mode<mid>;
    } else {
      // Above 512KB, high energy
      return @mode<high>;
    }
  }

  public EnergyData(List<Byte> bytes) {
    this.bytes = bytes;
  }

  public List<Byte> bytes() {
    return this.bytes;
  }

  public List<Byte> compress() {
    float compression = 1;
    boolean timeout = false;

    List<Byte> compressed = new ArrayList<>(this.bytes);
    while(compression < this.compressionRatio && !timeout) {
      // Complicated algorithm that compresses as close to the compression ratio as possible
      timeout = true;
    }

    return compressed;
  }

}
