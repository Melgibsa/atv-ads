#include <iostream>
#include <vector>
#include <cstdio>
#include <algorithm>

using namespace std;

struct usuario {
    int id;
    string nome;
    int idade;
    string produto;
    int quantidade;
};

usuario user() {
    usuario x;
    cin >> x.id >> x.nome >> x.idade >> x.produto >> x.quantidade;
    return x;
}

int totalVendas(const vector<int>& vendas) {
    int total = 0;
    for(int i = 0; i < vendas.size(); i++) {
    int quantidade = vendas[i];
    total += quantidade;
}
    return total;
}

vector<string> AcimaDe50(const vector<int>& vendas, const vector<string>& produtos) {
    vector<string> resultados;
    for(int i = 0; i < vendas.size(); i++) {
        if(vendas[i] > 50) {
            resultados.push_back(produtos[i]);
        }
    }
    return resultados;
}

vector<string> AbaixoDe10(const vector<int>& vendas, const vector<string>& produtos) {
    vector<string> resultados;
    for(int i = 0; i < vendas.size(); i++) {
        if(vendas[i] < 10) {
            resultados.push_back(produtos[i]);
        }
    }
    return resultados;
}

int maiorVenda(const vector<int>& vendas) {
    int maior = -1; // Inicializa com valor inválido
    for(int i = 0; i < vendas.size(); i++) {
        if(maior == -1 || vendas[i] > vendas[maior]) {
            maior = i;
        }
    }
    return maior;
}

double mediaIdades(const vector<usuario>& usuarios) {
    double soma = 0;
    for(int i = 0; i < usuarios.size(); i++) {
        soma += usuarios[i].idade;
    }
    return soma / usuarios.size();
}

int clienteAbaixo25(const vector<usuario>& usuarios) {
    int count = 0;
    for(int i = 0; i < usuarios.size(); i++) {
        if(usuarios[i].idade < 25) {
            count++;
        }
    }
    return count;
}

int clienteMaisVelho(const vector<usuario>& usuarios) {
    int indiceMaisVelho = 0;
    for(int i = 1; i < usuarios.size(); i++) {
        if(usuarios[i].idade > usuarios[indiceMaisVelho].idade) {
            indiceMaisVelho = i;
        }
    }
    return indiceMaisVelho;
}

void perfilMaisVendido(const vector<usuario>& usuarios, const vector<int>& vendas, const vector<string>& produtos) {
    // 1. Encontra o produto mais vendido
    int indiceMaisVendido = maiorVenda(vendas);
    if (indiceMaisVendido == -1) {
        printf("Nenhum produto vendido.\n");
        return;
    }

    string produtoMaisVendido = produtos[indiceMaisVendido];
    
    double somaIdades = 0.0;
    int totalClientes = 0;
    int jovens = 0, adultos = 0, idosos = 0;

    for (int i = 0; i < usuarios.size(); i++) {
        if (usuarios[i].produto == produtoMaisVendido) {
            somaIdades += usuarios[i].idade;
            totalClientes++;
            
            // Classifica por faixa etária
            if (usuarios[i].idade < 25) jovens++;
            else if (usuarios[i].idade < 60) adultos++;
            else idosos++;
        }
    }

    printf("\nPerfil dos compradores do produto mais vendido (%s):\n", produtoMaisVendido.c_str());
    
    if (totalClientes > 0) {
        printf("- Media de idade: %.1f anos\n", (somaIdades / totalClientes));
        printf("- Distribuicao por faixa etaria:\n");
        printf("  * Jovens (<25 anos): %d clientes\n", jovens);
        printf("  * Adultos (25-59 anos): %d clientes\n", adultos);
        printf("  * Idosos (60+ anos): %d clientes\n", idosos);
        
        if (jovens >= adultos && jovens >= idosos)
            printf("- Perfil predominante: Jovens\n");
        else if (adultos >= jovens && adultos >= idosos)
            printf("- Perfil predominante: Adultos\n");
        else
            printf("- Perfil predominante: Idosos\n");
    } else {
        printf("Nenhum cliente comprou este produto.\n");
    }
}

//FUNÇÃO PRINCIPAL
int main() {
    //PARTE I
    //Cadastro de Produtos
    printf("RELATÓRIO DE VENDAS\n");
    
    vector<int> vendas(10, 0);
    vector<string> produtos(10);
    for(int i = 0; i < 10; i++) {
        int id;
        string produto;
        cin >> id >> produto;
        produtos[id] = produto;
    }

    //Cadastro de usuários e contagem
    vector<usuario> usuarios;
    
    for(int i = 0; i < 10; i++) {
        usuario u = user();
        usuarios.push_back(u);
        
        bool encontrado = false;//verifica se o produto existe
        for(int j = 0; j < 10; j++) {
            if(produtos[j] == u.produto) {
                vendas[j] += u.quantidade;
                break;
            }
        }
        
    }

    //Contagem e produto mais vendido
    printf("\nVendas por produto:\n");
    bool tem_vendas = false;

    for(int i = 0; i < 10; i++) {
        if(vendas[i] > 0) {
            tem_vendas = true;
            printf("%s: %d unidades\n", produtos[i].c_str(), vendas[i]);
        }
    }

    int indice_maior = maiorVenda(vendas);
    if(indice_maior != -1) {
        printf("\nProduto mais vendido: %s (%d unidades)\n",
            produtos[indice_maior].c_str(), vendas[indice_maior]);
    } else {
        printf("\nNenhum produto vendido\n");
    }

    //PARTE II
    //Produtos acima de 50 e abaixo 10
    vector<string> acima50 = AcimaDe50(vendas, produtos);
    vector<string> abaixo10 = AbaixoDe10(vendas, produtos);

    //Produtos com mais de 50 unidades
    printf("\nProdutos que venderam mais de 50 unidades:\n");
    int count50 = 0;
    for(int i = 0; i < 10; i++) {  // Assumindo 10 produtos
    if(vendas[i] > 50) {
        printf("%s: %d unidades\n", produtos[i].c_str(), vendas[i]);
        count50++;
        }
    }
    if(count50 == 0) {
        printf("Nenhum produto vendeu mais de 50 unidades\n");
    }

    //Produtos com menos de 10 unidades
    printf("\nProdutos que venderam menos de 10 unidades:\n");
    int count10 = 0;
    for(int i = 0; i < 10; i++) {
        if(vendas[i] > 0 && vendas[i] < 10) {
                printf("%s: %d unidades - ALERTA DE DESEMPENHO\n", produtos[i].c_str(), vendas[i]);
            count10++;
        }
    }
    if(count10 == 0) {
        printf("Nenhum produto vendeu menos de 10 unidades\n");
    }
    
    //Total geral
    int total = totalVendas(vendas);
    printf("\nTotal geral de vendas da semana: %d\n", total);

    //PARTE III
    //Cálculos com as idades dos usuários
    printf("\nRELATÓRIO DO PERFIL DOS CLIENTES\n");
    
    double media = mediaIdades(usuarios);
    printf("\nMédia de idade dos usuários: %.2f\n", media);
    
    int jovens = clienteAbaixo25(usuarios);
    printf("Clientes com menos de 25 anos: %d\n", jovens);
    
    int indice = clienteMaisVelho(usuarios);
    if(indice != -1) {
        printf("Cliente mais velho: %s(%d anos)\n",
               usuarios[indice].nome.c_str(), usuarios[indice].idade);
    } else {
        printf("Nenhum cliente cadastrado\n");
    }

    //Perfil(por idade) que mais compra o produto mais vendido
    perfilMaisVendido(usuarios, vendas, produtos);
    
    return 0;
}