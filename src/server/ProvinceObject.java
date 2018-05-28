package server;

import model.IRemoteProvince;
import model.Province;
import java.rmi.server.*;
import java.rmi.*;
import java.util.ArrayList;
 
/**
 * Server object
 * 
 */
public class ProvinceObject extends UnicastRemoteObject implements IRemoteProvince {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 11L;

public ProvinceObject() throws RemoteException {
    super();
  }
 
        @Override
  public int save(Province p) {
    try {
      System.out.println("Invoke save from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
    return ProvinceRepository.save(p);
  }
 
        @Override
  public int update(Province p) {
    try {
      System.out.println("Invoke update from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
    return ProvinceRepository.update(p);
  }
 
        @Override
  public int delete(Province p) {
    try {
      System.out.println("Invoke delete from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
    return ProvinceRepository.delete(p);
  }
 
        @Override
  public void deleteAll() {
    try {
      System.out.println("Invoke deleteAll from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
    ProvinceRepository.deleteAll();
  }
 
        @Override
  public ArrayList findAll() {
    try {
      System.out.println("Invoke findAll from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
    return ProvinceRepository.findAll();
  }
 
        @Override
  public ArrayList findByName(String criteria) {
    try {
      System.out.println("Invoke findByName from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
    return ProvinceRepository.findByName(criteria);
  }
}

