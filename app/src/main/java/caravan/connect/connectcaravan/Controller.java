package caravan.connect.connectcaravan;



import java.util.HashMap;

public class Controller {
    private static Controller controller;
    private static Bluetooth bluetooth;



    public static Controller getController() {
        if(controller == null) {
            controller = new Controller();
            bluetooth = new Bluetooth();

        }
        return controller;
    }

    public HashMap<String, String> getDevicesBluetooth(){
        return bluetooth.getDevices();
    }

    public void addDevice(String id, String name){
        bluetooth.addDevice(id, name);
    }


}
