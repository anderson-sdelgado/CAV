# Implementation Plan - Testes para Room Variable Datasources

Implementar testes unitários para as classes de datasource do Room na pasta `br.com.usinasantafe.cav.external.room.datasource.variable`.

## Proposed Changes

### [infra]

Criar os seguintes arquivos de teste na pasta `app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/`:

#### [NEW] [ICardRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/ICardRoomDatasourceTest.kt)
#### [NEW] [IEquipSecRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IEquipSecRoomDatasourceTest.kt)
#### [NEW] [IInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IInvolvedRoomDatasourceTest.kt)
#### [NEW] [IPassengerColabRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerColabRoomDatasourceTest.kt)
#### [NEW] [IPassengerInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerInvolvedRoomDatasourceTest.kt)
#### [NEW] [IVehicleInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleInvolvedRoomDatasourceTest.kt)
#### [NEW] [IVehicleOwnRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleOwnRoomDatasourceTest.kt)
#### [NEW] [IWitnessRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IWitnessRoomDatasourceTest.kt)

Cada classe de teste seguirá o padrão:
- Uso de `RobolectricTestRunner`.
- Banco de dados em memória (`Room.inMemoryDatabaseBuilder`).
- Teste de `add`:
    - Sucesso: Verifica se o ID retornado é maior que 0 e se o dado foi realmente inserido no banco (usando o método `all()` do DAO).
    - Falha: Embora o método `add` apenas chame o `insert`, podemos simular falhas de restrição se houver chaves estrangeiras ou campos obrigatórios (embora os modelos já garantam isso em sua maioria).

## Verification Plan

### Automated Tests
- Executar os testes unitários criados:
  `./gradlew :app:testDebugUnitTest --tests "br.com.usinasantafe.cav.external.room.datasource.variable.*"`
