package br.com.teste.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdicionarContatoServelet
 */
@WebServlet("/adicionarContato")
public class AdicionarContatoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataEmTexto = request.getParameter("dataNascimento");
		
		Calendar dataNascimento = null;
		
		// fazendo a conversão da data
		
		try {
			 Date date = new SimpleDateFormat("dd/MM/yyyy")
                     .parse(dataEmTexto);
			 dataNascimento = Calendar.getInstance();
             dataNascimento.setTime(date);
		} catch (Exception e) {
			out.println("Erro de conversão da data");
            return; //para a execução do método
           
		}
		
		Contato contato = new Contato();
		contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setDataNascimento(dataNascimento);
        
        
        ContatoDao dao = new ContatoDao();
        dao.adicionar(contato);
        
        out.println("<html>");
        out.println("<body>");
        out.println("Contato " + contato.getNome() +
                " adicionado com sucesso");        
        out.println("</body>");
        out.println("</html>");
	}

}
