package com.freedom.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * TODO
 *
 * @author tobebetter9527
 * @create 2022/05/15 15:31
 */
public class ShelfTest {

  @Test
  public void shelfCanAcceptAndReturnItem() {
    Shelf shelf = new Shelf();
    shelf.put("Orange");
    Assert.assertTrue(shelf.take("Orange"));
  }

  @Test
  public void checkPutMethod() {
    Shelf shelf = new Shelf();
    shelf.put(null);
    Assert.assertFalse(shelf.take(null));

    shelf.put("");
    Assert.assertFalse(shelf.take(""));

    shelf.put("123");
    Assert.assertTrue(shelf.take("123"));
    Assert.assertFalse(shelf.take("123"));

    shelf.put("345");
    shelf.put("345");
    Assert.assertTrue(shelf.take("345"));
    Assert.assertTrue(shelf.take("345"));
    Assert.assertFalse(shelf.take("345"));
  }

}
