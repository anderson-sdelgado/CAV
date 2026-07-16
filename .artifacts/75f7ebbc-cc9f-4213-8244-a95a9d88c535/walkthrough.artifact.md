# Walkthrough - Melhoria da classe `ProcessWorkManager`

Refatorei a classe `ProcessWorkManager` para implementar um fluxo de envio de dados mais robusto e confiável em segundo plano.

## Alterações Realizadas

### [domain] - Background

#### [ProcessWorkManager.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/domain/usecases/background/ProcessWorkManager.kt)

- **Fluxo de Trabalho Consistente**: Agora o worker segue uma sequência lógica rigorosa:
    1. Valida a existência de configuração.
    2. Verifica se há cards pendentes para envio.
    3. Sinaliza o início do envio (`StatusSend.SEND`).
    4. Tenta realizar o envio.
    5. Em caso de sucesso, sinaliza a conclusão (`StatusSend.SENT`).
- **Tratamento de Erros Centralizado**: Implementei o uso de `runCatching` para capturar qualquer exceção durante o processo, garantindo que falhas sejam logadas via `handleFailure`.
- **Estratégia de Retentativa**: O Worker agora retorna `Result.retry()` se o envio falhar ou se uma exceção ocorrer, permitindo que o Android reagende a tarefa automaticamente seguindo os critérios definidos no `StartWorkManager`.

## Verificação

O código foi revisado para garantir compatibilidade com os Use Cases injetados (`GetConfig`, `SetStatusSend`, `HasSendCard`, `SendCard`) e com as políticas de backoff do `WorkManager` já configuradas no projeto.
