<h1>Веб-Сайт "Конференция"</h1>

Сначала нужно залогиниться (список доступных пользователей находится в классе TheConferenceTestTaskApplication в методе runner 
(инициализируются в БД при запуске))
В зависимости от роли страница будет перенаправлена, в случае успешной авторизации
У каждой роли свои возможности:
  Админ: Удаление и редактирование пользователей
  Презентер: создание, удаление и редактирование презентаций
  Обычный пользователь: доступен только просмотр презентаций
В качестве СУБД используется PostgreSQL
