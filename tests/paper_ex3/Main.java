package paper_ex3;

import java.util.*;

modes { energy_saver <: managed; managed <: full_throttle; }

class Main {
  public static void main(String[] args) {
    Agent a = new Agent@mode<managed>(args[0]);
    ArraySet urls = new ArraySet(args[1]);
    while(true) {
      ArraySet newbie = new ArraySet();
      for (String s : urls) {
        newbie = a.work(s);
        urls.remove(s).add(newbie);
      }
    }
  }
}
