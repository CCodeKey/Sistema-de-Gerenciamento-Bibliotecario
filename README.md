# Sistema Bibliotecário com POO - Java
Um sistema simples para gerenciar empréstimos de livros em uma biblioteca. Foi desenvolvido em Java e conta com algumas funcionalidades:
- Cadastro de obras (Livros, Revistas, Artigos)
- Controle de usuários (Alunos, Professores, Funcionaários, Administradores, Bibliotecários e Estagiários)
- Empréstimos, devoluções e pagamentos
- Relatórios em PDF

## 🚀 Como usar
1. Requisitos: Java 17+ instalado
2. clonar o repositório
```git
git clone https://github.com/CCodeKey/Sistema-de-Gerenciamento-Bibliotecario/
```
3. Executar:
```bash
java -jar biblioteca.jar
```

## ⚙️ Tecnologias utilizadas
- Java
- iText
- Gson

## 📂 Estrutura do Projeto
```text
src
├── Biblioteca/  
    ├── model/      # Classes (Livro, Usuario, etc.)  
    ├── dao/        # Salvar dados em JSON  
    ├── view/       # Telas (Java Swing)  
    └── controller/ # Lógica do sistema
├── Excecoes/       # Classes de Exceções
├── TypeAdapter/    # Classes de conversão e tipagem
├── Interface/      # Interfaces de relatórios e DAOs
├── lib/            # Jar das bibliotecas utilizadas no projeto
├── Relatorio/      # Classes que geram os relatórios
└── resources/
    ├── json/       # Arquivos Json
    └── pdf/        # Relatórios PDF
```

## Autores:
<table>
    <tr>
        <td align="center">
            <a href="https://github.com/CCodekey">
                <img src="https://avatars.githubusercontent.com/u/105808889?v=4" width="100px;" alt="Gabriel T."/><br>
                <sub>
                    <b>Gabriel Tertuliano</b>
                </sub>
            </a>
        </td>
        <td align="center">
            <a href="https://github.com/kaleu-victor">
                <img src="https://avatars.githubusercontent.com/u/169067294?v=4" width="100px;" alt="Kaléu V."/><br>
                <sub>
                    <b>Kaléu Victor</b>
                </sub>
            </a>
        </td>
    </tr>
</table>
