import com.example.Bibliotecario;
import com.example.Livro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BibliotecarioTeste {

    // TESTE 1 - CADASTRAR LIVROS
    
    @Test
    public void testCadastrarLivro() {
        Livro livroMock = Mockito.mock(Livro.class);
        when(livroMock.getTitulo()).thenReturn("Dom Casmurro");

        Bibliotecario bibliotecario = new Bibliotecario("João", "12345");

        // Vamos cadastrar dom casmurros
        bibliotecario.cadastrarLivro(livroMock);

        // Temos que saber o nome, então tem que chamar uma vez
        verify(livroMock, times(1)).getTitulo();

        // Se for cadastrado corretamente o tamanho será de 1 nas atividades.
        assertEquals(1, bibliotecario.getAtividades().size());
        assertEquals("Cadastro do livro Dom Casmurro", bibliotecario.getAtividades().get(0));
    }

    // TESTE 2 - REALIZAR EMPRESTIMOS 

    @Test
    public void testRealizarEmprestimo() { 
        Livro livroMock = Mockito.mock(Livro.class);

        // Comportamento para o nosso mock. Retornar "Crepúsculo" quando o método getTitulo for chamado
        when(livroMock.getTitulo()).thenReturn("Crepúsculo");

        // Criar um bibliotecário para usar o método
        Bibliotecario yan = new Bibliotecario("Yan", "1111");
        yan.autenticar("Yan", "1111");
        yan.realizarEmprestimo(livroMock, "Eduardo");

        // Comparar se o getAtividades está correto (deve ser igual a um)
        assertEquals(1, yan.getAtividades().size());

        // Verificar se o método getTitulo foi chamado uma vez (precisamos saber qual livro será emprestado)
        verify(livroMock, times(1)).getTitulo();
    }

    // TESTE 3 - VERIFICAR SE A DEVOLUÇÃO DO LIVRO TA OK;
    
    @Test
    public void testpodeRealizarDevolucao() {
        Bibliotecario Eduardo = new Bibliotecario("Eduardo", "1911");
        Livro livroMock = Mockito.mock(Livro.class);

        // retorne true qnd perguntar se o livro pode ser emprestado.
        when(livroMock.podeSerEmprestado()).thenReturn(true);
        
        //agora testei se Eduardo pode realizar devolução 
        assertTrue(Eduardo.podeRealizarDevolucao(livroMock));
    }

    // TESTE 4 - TENTIVATIVA DE REALIZAR UM EMPRESTIMO SEM  TA AUTENTICADO CORRETAMENTE

    @Test
    public void testEmprestarSemTaAutenticado() {
        Livro livroMock = Mockito.mock(Livro.class);
        when(livroMock.getTitulo()).thenReturn("O Senhor dos Anéis");
    
        Bibliotecario bibliotecario = new Bibliotecario("João", "12345");
    
        assertFalse(bibliotecario.autenticar("Joao", "3456"));
    
        // Não autenticado corretamente, ele pode emprestar?
        bibliotecario.realizarEmprestimo(livroMock, "Fulano");
    
        // Verifique se o método getTitulo foi chamado uma vez (precisamos saber qual livro será emprestado)
        verify(livroMock, times(1)).getTitulo();
    
        //Verifique se foi registrado
        assertEquals(1, bibliotecario.getAtividades().size());
    }

    // TESTE 5 - REGISTRAR PERDA FUNCIONANDO

    @Test
    public void testRegistrarPerda() {
        Livro livroMock = Mockito.mock(Livro.class);
        when(livroMock.getTitulo()).thenReturn("Harry Potter");

        Bibliotecario bibliotecario = new Bibliotecario("João", "12345");
        bibliotecario.registrarPerda(livroMock);

        verify(livroMock, times(1)).getTitulo();
        // deve dar errado, se registrar perda tá ok
        assertEquals(0, bibliotecario.getAtividades().size());
        assertEquals("Registro de perda do livro Harry Potter", bibliotecario.getAtividades().get(0));

    }


}
