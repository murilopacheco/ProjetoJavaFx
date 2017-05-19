package relatorios.GeraRelatorios;

import Model.Cliente;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

/**
 * Created by muril on 18/05/2017.
 */
public class RelatorioClientes {

    private String path; //Caminho base

    private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper

    //Recupera os caminhos para que a classe possa encontrar os relatórios
    public RelatorioClientes() {
        this.path = this.getClass().getClassLoader().getResource("").getPath();
        this.pathToReportPackage = this.path + "/relatorios/";
        System.out.println(path);
    }


    //Imprime/gera uma lista de Clientes
    public void imprimir(List<Cliente> clientes) throws Exception
    {
        JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "RelatorioClientes.jrxml");

        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(clientes));

        JasperExportManager.exportReportToPdfFile(print, "c:/Relatorio_de_Clientes.pdf");
    }

    public String getPathToReportPackage() {
        return this.pathToReportPackage;
    }

    public String getPath() {
        return this.path;
    }
}

