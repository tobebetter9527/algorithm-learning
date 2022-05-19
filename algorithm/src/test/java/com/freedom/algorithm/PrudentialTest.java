package com.freedom.algorithm;

import java.util.concurrent.Callable;

/**
 * TODO
 *
 * @author tobebetter9527
 * @create 2022/05/19 22:55
 */
public class PrudentialTest {

}


interface Reptile {

  ReptileEgg lay();
}

class FireDragon implements Reptile {

  public FireDragon() {
  }

  public static void main(String[] args) throws Exception {
    FireDragon fireDragon = new FireDragon();
    System.out.println(fireDragon instanceof Reptile);
  }

  @Override
  public ReptileEgg lay() {
    return new ReptileEgg(() -> new FireDragon());
  }
}

class ReptileEgg {

  private volatile boolean hatchFlag = false;
  private Callable<Reptile> createReptile;


  public ReptileEgg(Callable<Reptile> createReptile) {
    this.createReptile = createReptile;
  }

  public Reptile hatch() throws Exception {
    if (hatchFlag) {
      throw new IllegalStateException("hatch only once");
    }

    hatchFlag = true;

    return createReptile.call();
  }
}