package Service;

import Model.Pessoa;

import java.util.List;

/**
 * Created by murilo on 07/02/2017.
 */
public interface PessoaService {

    public void Salvar(Pessoa pessoa);

    public List<Pessoa> listar();

    public Pessoa buscaPorId(int id);

    public void apagar(int id);

    public void atualizar(Pessoa pessoa);

    public static PessoaService getNewInstance(){
        return new PessoaService() {
            @Override
            public void Salvar(Pessoa pessoa) {

            }

            @Override
            public List<Pessoa> listar() {
                return null;
            }

            @Override
            public Pessoa buscaPorId(int id) {
                return null;
            }

            @Override
            public void apagar(int id) {

            }

            @Override
            public void atualizar(Pessoa pessoa) {

            }
        };
    }
}
