/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ChamadoDAO;
import br.edu.ifsul.modelo.Chamado;
import br.edu.ifsul.modelo.Movimento;
import java.util.Calendar;

/**
 *
 * @author Ricardo
 */
public class teste {
    
    
    public static void main(String[] args) {
        ChamadoDAO<Chamado> dao = new ChamadoDAO<>();
        Chamado c = dao.localizar(17);
        c.setData_ocorrencia(Calendar.getInstance());
        dao.merge(c);
        try {
        for(Chamado c1 : dao.getListaObjetos()){
            System.out.println("id: " + c1.getId());
            for (Movimento m : c1.getMovimentos()){
                System.out.println(m.getId());
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
