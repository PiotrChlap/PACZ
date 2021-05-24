Do poprawnego działania aplikacji należy zainstalować aplikacje postgresql oraz stworzyć konto z loginem "postgres" oraz hasłem: "ramzes" o nazwie "database"
lub zmienić dane w Klasie DAO we wszystkich 3 atrybutach.
Skrypt tworzący baze danych jest pliku databasescript.sql

Należy także w środowisku INTELIJ dodać w konfiguracji w konfiguracji uruchowmienia main atrubyt VM option a wniej "--module-path ${ścieżka} --add-modules=javafx.controls,javafx.fxml"
gdzie ścieżka to ścieżka do zainstalowanego FXML. Także należy dodać plik postgressql-42.2.19.jar do struktury projektu. File->Project Structure->Libriaries-> Add new Libiraries->
wybrać plik postgressql-42.2.19.jar i zaznaczyć PACZ,VIEW oraz MODEL.

Autorzy:
Chłapiński Piotr 229853
Mateusz Stawiany 230008