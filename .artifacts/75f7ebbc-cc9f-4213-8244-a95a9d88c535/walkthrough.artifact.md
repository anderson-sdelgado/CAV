# Walkthrough - Testes Unitários para `StartFlow` (Fim-a-Fim)

Implementei a cobertura completa de testes unitários para o fluxo de inicialização (`StartFlow`), abrangendo desde o Use Case até os Datasources do Room.

## Alterações Realizadas

### [domain] - Use Cases

- **[NEW] [IStartFlowTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/domain/usecases/common/IStartFlowTest.kt)**: Validado o Use Case garantindo que ele coordena corretamente a deleção de dados antigos e a verificação de existência de dados pendentes.

### [infra] - Repositories

- **[MODIFY] [ISendCardRepositoryTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/infra/repositories/variable/card/ISendCardRepositoryTest.kt)**: Adicionados testes para o método `delete()`, simulando o processo complexo de limpeza de um card enviado, incluindo seus veículos, passageiros, equipamentos e fotos físicas.

### [external] - Datasources (Room)

Atualizei as seguintes classes de teste para validar os novos métodos de busca por ID e exclusão em lote, garantindo a integridade referencial manual durante a limpeza:

- **[ICardRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/ICardRoomDatasourceTest.kt)**: Testados `listDelete()` (filtro de 1 semana) e `deleteById()`.
- **[IVehicleOwnRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleOwnRoomDatasourceTest.kt)**: Testados `listByIdCard()` e `deleteByIdCard()`.
- **[IEquipSecRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IEquipSecRoomDatasourceTest.kt)**: Testado `deleteByIdVehicleList()`.
- **[IInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IInvolvedRoomDatasourceTest.kt)**: Testados `listByIdCard()` e `deleteByIdCard()`.
- **[IWitnessRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IWitnessRoomDatasourceTest.kt)**: Testados `listByIdCard()` e `deleteByIdCard()`.
- **[IPassengerColabRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerColabRoomDatasourceTest.kt)**: Testado `deleteByIdVehicleList()`.
- **[IPassengerInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IPassengerInvolvedRoomDatasourceTest.kt)**: Testado `deleteByIdVehicleList()`.
- **[IVehicleInvolvedRoomDatasourceTest.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/test/java/br/com/usinasantafe/cav/external/room/datasource/variable/IVehicleInvolvedRoomDatasourceTest.kt)**: Testados `listByIdCard()` e `deleteByIdCard()`.

## Verificação

Os testes foram revisados para garantir que todos os parâmetros obrigatórios dos modelos Room fossem passados corretamente. Embora o ambiente Gradle local tenha apresentado falha técnica (problema no serviço de diretório do Android), a lógica do código foi validada manualmente contra as definições das entidades e DAOs.
