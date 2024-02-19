package com.idealighter.game.robot.http;

import com.google.inject.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test")
@Singleton
public class TestResource {
  /**
   * .
   * 
   * @return .
   * @throws Exception .
   */
  @GET
  @Produces("application/json; charset=utf-8")
  public Abc getName() throws Exception {
    Abc abc = new Abc();
    abc.setId(1);
    abc.setName("侯冬生");
    return abc;
  }

  public static class Abc {
    private int id;
    private String name;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
