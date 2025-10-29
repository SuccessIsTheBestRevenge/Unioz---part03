package com.example.aplicaounioz; // Substitua pelo nome do seu pacote

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat; // Para usar cores

public class menu_activity extends AppCompatActivity {

    // 1. Declarar variáveis de componente
    private EditText editTextMensagem;
    private Button buttonEnviar;
    private LinearLayout layoutMensagens;
    private ScrollView scrollViewChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 2. Ligar as variáveis aos IDs do XML (findViewById)
        editTextMensagem = findViewById(R.id.edit_text_mensagem);
        buttonEnviar = findViewById(R.id.button_enviar);
        layoutMensagens = findViewById(R.id.layout_mensagens);
        scrollViewChat = findViewById(R.id.scroll_view_chat);

        // 3. Configurar a ação do botão
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagem();
            }
        });

        // Adiciona a primeira mensagem do bot ao abrir o app
        adicionarMensagemBot(" Olá! Eu sou o assistente virtual da faculdade. Por favor, **informe seu problema** ou digite uma palavra-chave como 'horário' ou 'matrícula'.", true);
    }

    private void enviarMensagem() {
        String mensagem = editTextMensagem.getText().toString().trim();

        if (!mensagem.isEmpty()) {
            // A. Adiciona a mensagem do usuário
            adicionarMensagemUsuario(mensagem);

            // B. Processa e adiciona a resposta do bot (simulação)
            String resposta = processarRespostaAutomatica(mensagem);
            adicionarMensagemBot(resposta, false);

            // C. Limpa o campo de texto
            editTextMensagem.setText("");

            // D. Rola o chat para baixo (visualização da última mensagem)
            scrollViewChat.post(new Runnable() {
                @Override
                public void run() {
                    scrollViewChat.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    // --- LÓGICA DAS RESPOSTAS AUTOMÁTICAS ---
    private String processarRespostaAutomatica(String mensagem) {
        String msgLowerCase = mensagem.toLowerCase();

        // **A Lógica Simples com if/else if/else**
        if (msgLowerCase.contains("horário") || msgLowerCase.contains("atendimento")) {
            return " Nosso horário de atendimento presencial é de segunda a sexta, das 8h às 18h. Para dúvidas gerais, utilize o portal do aluno.";
        } else if (msgLowerCase.contains("matrícula") || msgLowerCase.contains("aluno")) {
            return " O processo de matrícula/rematrícula é feito exclusivamente pelo Portal do Aluno. Você pode acessá-lo no nosso site.";
        } else if (msgLowerCase.contains("problema")) {
            // Resposta específica solicitada pelo usuário
            return " Certo. Para que eu possa encaminhar para o setor correto, **informe seu tipo do problema**, por favor. (Ex: 'Financeiro', 'Portal', 'Professor').";
        } else if (msgLowerCase.contains("financeiro") || msgLowerCase.contains("boleto")) {
            return " Dúvidas financeiras? Por favor, ligue para (11) 5555-1234. Nosso sistema de boletos está temporariamente indisponível.";
        } else if (msgLowerCase.contains("obrigado") || msgLowerCase.contains("valeu")) {
            return " De nada! Fico feliz em ajudar. Se tiver mais dúvidas, pergunte.";
        } else {
            // Resposta padrão
            return " Desculpe, não consegui entender sua pergunta. Por favor, seja mais específico ou tente uma das palavras-chave: 'horário', 'matrícula', 'problema' ou 'financeiro'.";
        }
    }
    // ----------------------------------------

    // Função para criar e adicionar o balão do USUÁRIO (Lado Direito)
    private void adicionarMensagemUsuario(String texto) {
        TextView textView = criarTextViewBase(texto);

        // Configuração do layout do balão (Usuário: Alinhado à direita, cor primária)
        textView.setBackgroundResource(R.drawable.bg_balao_usuario); // Balão do Usuário (cor branca/clara)
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END; // Alinha à direita
        params.topMargin = 4;
        params.bottomMargin = 4;
        textView.setLayoutParams(params);

        layoutMensagens.addView(textView);
    }

    // Função para criar e adicionar o balão do BOT (Lado Esquerdo)
    private void adicionarMensagemBot(String texto, boolean isPrimeiraMsg) {
        TextView textView = criarTextViewBase(texto);

        // Configuração do layout do balão (Bot: Alinhado à esquerda, cor secundária)
        textView.setBackgroundResource(R.drawable.bg_balao_chat); // Balão do Bot (cor verde/azul)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.START; // Alinha à esquerda
        params.topMargin = isPrimeiraMsg ? 0 : 4; // Sem margem superior para a primeira mensagem
        params.bottomMargin = 4;
        textView.setLayoutParams(params);

        layoutMensagens.addView(textView);
    }

    // Função auxiliar para configurar o TextView (Balão)
    private TextView criarTextViewBase(String texto) {
        TextView textView = new TextView(this);
        textView.setText(texto);
        textView.setPadding(12, 8, 12, 8);
        textView.setMaxWidth(getScreenWidth() * 2 / 3); // Limita o tamanho para 2/3 da tela
        return textView;
    }

    // Função simples para obter a largura da tela (melhora a exibição)
    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }
}