# Implementation Plan - Testes Unitários para `send()` no `ISendCardRepository`

Este plano visa completar a classe de teste `ISendCardRepositoryTest` adicionando os cenários de teste para o método `send()`, além de ajustar os mocks necessários.

## Proposed Changes

### [test] - Infra Repositories

#### [MODIFY] [ISendCardRepositoryTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/infra/repositories/variable/card/ISendCardRepositoryTest.kt)

1.  **Adicionar Mock**: Adicionar o mock para `CardRetrofitDatasource`.
2.  **Atualizar Construtor**: Passar o novo mock para o construtor do `repository`.
3.  **Cenários de Erro em `send()`**:
    - Falha ao buscar card (`cardRoomDatasource.getSend()`).
    - Falha ao buscar dados relacionados (ex: `vehicleOwnRoomDatasource.listByIdCard()`).
    - Falha no envio via Retrofit (`cardRetrofitDatasource.send()`).
4.  **Cenário de Sucesso em `send()`**:
    - Simular o retorno de todos os datasources do Room.
    - Verificar se o `cardRetrofitDatasource.send()` foi chamado com o objeto convertido corretamente.
    - Validar que o retorno final é `isSuccess`.

## Verification Plan

### Automated Tests
- Executar os testes unitários atualizados:
  `./gradlew :app:testDebugUnitTest --tests "br.com.usinasantafe.cav.infra.repositories.variable.card.ISendCardRepositoryTest"`
