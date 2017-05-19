package relatorios.GeraRelatorios;

import Model.Cliente;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by muril on 18/05/2017.
 */
public class RelatorioClientes {

    private String path; //Caminho base

    private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper

    //Recupera os caminhos para que a classe possa encontrar os relatórios
    public RelatorioClientes() {
        this.path = this.getClass().getClassLoader().getResource("").getPath();
        this.pathToReportPackage = this.path + "relatorios/templates/";
        System.out.println(path);
    }


    //Imprime/gera uma lista de Clientes
    public void imprimir(List<Cliente> clientes) throws Exception    {

        Map parameter = new HashMap();
        parameter.put("logo",this.getClass().getClassLoader().getResource("").getPath()  + "relatorios/img/logo.png");


        JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "relatorioClientes.jrxml");

        JasperPrint print = JasperFillManager.fillReport(report, parameter, new JRBeanCollectionDataSource(clientes));

        JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\murilo\\Documents\\alfa\\lp2\\Relatorio_de_Clientes.pdf");
    }

    public String getPathToReportPackage() {
        return this.pathToReportPackage;
    }

    public String getPath() {
        return this.path;
    }
}

