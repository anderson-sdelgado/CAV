# Implementation Plan - Testes Unitários para `StartFlow` (Fim-a-Fim)

Implementar a cobertura de testes para o Use Case `StartFlow`, abrangendo desde a camada de domínio até a camada de infraestrutura (Repositório e Datasources), seguindo o padrão estabelecido no aplicativo.

## Proposed Changes

### [domain] - Use Cases

#### [NEW] [IStartFlowTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/domain/usecases/common/IStartFlowTest.kt)
- Testar falha no `cardRepository.delete()`.
- Testar falha no `cardRepository.has()`.
- Testar sucesso retornando `true` (tem dados) e `false` (não tem dados).

### [infra] - Repositories

#### [MODIFY] [ISendCardRepositoryTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/infra/repositories/variable/card/ISendCardRepositoryTest.kt)
- Adicionar testes para o método `delete()`:
    - Simular falha em cada etapa do processo de deleção (ex: `listDelete`, `listByIdCard`, `deleteByIdVehicleList`, etc.).
    - Simular cenário de sucesso, verificando se todos os datasources de limpeza foram chamados e se os arquivos de fotos foram processados.

### [external] - Datasources (Room)

Atualizar as classes de teste de Datasource para incluir a validação dos novos métodos de busca por ID de card/veículo e métodos de deleção:

#### [MODIFY] [ICardRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/ICardRoomDatasourceTest.kt)
- Testar `listDelete()`: Validar se retorna apenas cards com `StatusSend.SENT` e data inferior a 1 semana.
- Testar `deleteById()`: Validar se o registro é removido do banco.

#### [MODIFY] [IVehicleOwnRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleOwnRoomDatasourceTest.kt)
- Testar `listByIdCard()` e `deleteByIdCard()`.

#### [MODIFY] [IEquipSecRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IEquipSecRoomDatasourceTest.kt)
- Testar `deleteByIdVehicleList()`.

#### [MODIFY] [IInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IInvolvedRoomDatasourceTest.kt)
- Testar `listByIdCard()` e `deleteByIdCard()`.

#### [MODIFY] [IWitnessRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IWitnessRoomDatasourceTest.kt)
- Testar `listByIdCard()` e `deleteByIdCard()`.

#### [MODIFY] [IPassengerColabRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerColabRoomDatasourceTest.kt)
- Testar `deleteByIdVehicleList()`.

#### [MODIFY] [IPassengerInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerInvolvedRoomDatasourceTest.kt)
- Testar `deleteByIdVehicleList()`.

#### [MODIFY] [IVehicleInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleInvolvedRoomDatasourceTest.kt)
- Testar `listByIdCard()` e `deleteByIdCard()`.

## Verification Plan

### Automated Tests
- Executar a suite completa de testes:
  `./gradlew :app:testDebugUnitTest`
