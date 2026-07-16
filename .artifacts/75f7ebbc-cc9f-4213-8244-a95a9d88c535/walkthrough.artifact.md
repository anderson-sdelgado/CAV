# Walkthrough - Implementação de Testes para Room Datasources (Variable)

Implementei os testes unitários para todos os datasources do Room na pasta `variable`, garantindo a integridade das operações de inserção de dados.

## Alterações Realizadas

### [infra]

Criei 8 novos arquivos de teste na pasta `br.com.usinasantafe.cav.external.room.datasource.variable`:

- [ICardRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/ICardRoomDatasourceTest.kt)
- [IEquipSecRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IEquipSecRoomDatasourceTest.kt)
- [IInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IInvolvedRoomDatasourceTest.kt)
- [IPassengerColabRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerColabRoomDatasourceTest.kt)
- [IPassengerInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerInvolvedRoomDatasourceTest.kt)
- [IVehicleInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleInvolvedRoomDatasourceTest.kt)
- [IVehicleOwnRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleOwnRoomDatasourceTest.kt)
- [IWitnessRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IWitnessRoomDatasourceTest.kt)

### Detalhes da Implementação

1.  **Padronização**: Todos os testes utilizam `RobolectricTestRunner` e banco de dados em memória (`in-memory database`), seguindo o padrão já estabelecido para os datasources `stable`.
2.  **Validação de Inserção**: Cada teste valida se o método `add` insere o registro corretamente e retorna o ID gerado pelo banco.
3.  **Consistência com DatabaseRoom**: Os testes foram ajustados para utilizar os nomes corretos dos métodos de DAO expostos na classe `DatabaseRoom.kt` (ex: `colabCardDao()` e `equipCardDao()`).

## Verificação

> [!NOTE]
> Os testes foram rigorosamente revisados para garantir que as propriedades dos modelos (`CardRoomModel`, `VehicleOwnRoomModel`, etc.) e os tipos de dados estejam corretos. Devido a limitações do ambiente Gradle, a execução automatizada não foi possível, mas a estrutura segue fielmente os testes `stable` que já funcionam no projeto.
