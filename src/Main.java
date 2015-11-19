import java.sql.SQLException;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;


public class Main {

	public static void main(String[] args) throws SQLException {
		
		SACliente c = new SAClienteImp();
		System.out.println(c.consultarUnCliente(1));

	}

}
