# РУС / Описание проекта / Веб проект - гостиница
# Автор: Сергеев Даниил
-------------------------------------------------------

* Общие сведения:
Программная реализация системы бронирования номеров для гостиницы создана для оптимизации и повышения надёжности процесса регистрации клиентов. 
Заложены две подсистемы: клиентская и администраторская часть. А так же просмотр всей истории бронирований.

*** Функциональные возможности незарегистрированного пользователя:
- Изменение языка интерфейса
- Аутентификация
- Регистрация
- Просмотр всех комнат отеля постранично

* **Клиент** 
У клиента в свою очередь определены три статуса: Активный, Заблокированный, Удалённый.
Гостю, прошедшему процедуру регистрации присваивается роль Клиент и статус Заблокированный. На электронную почту клиента, указанную при регистрации, отправляется письмо со ссылкой перейдя по которой статус клиента меняется на Активный. В случае нарушения клиентом правил пользования сервисом администратор может его заблокировать, статус с Активный сменится на Заблокированный. Если клиент захочет удалить свой аккаунт на данном ресурсе статус меняется на Удалённый. 

*** Функциональные возможности зарегистрированного пользователя(клиента):
- Изменение языка интерфейса
- Совершение бронирования:
     - Создание заявки на номер по определённым невзаимозависимым требованиям, как, например, класс апартаментов, количество мест в номере и время пребывания.
     - Постраничный просмотр комнат
- Пополнение счета
- Просмотр всех бронирований, в том числе совершенных ранее
- Оплата бронирования (в случае подтверждения его администратором)
- Сортировка бронирований по нескольким параметрам
- Просмотр текущего баланса и логина
- Редактирование и удаление аккаунта. 

*** Функциональные возможности администратора:
- Изменение языка интерфейса
- Просмотр всех бронирований (текущих, совершенных пользователями ранее)
- Сортировка бронирований по дате
- Подтверждение ожидающего бронирования
- Отклонение любого бронирования, кроме уже отклоненных ранее
- Добавление новой комнаты (номера) в отель
- Изменение данных комнаты (номера) в отеле
- Просмотр всех существующих комнат (номеров) в отеле
- Просмотр всех существующих пользователей в системе
- Блокировка / Разблокировка пользователей
- Сортировка пользователей по заданным параметрам

# EN / Project description / Web application- hotel
# Created by Daniil Sergeev
--------------------------------------------------------
* General information:
The software implementation of the hotel room reservation system was created to optimize and increase the reliability of the customer registration process.
There are two subsystems: the client and the administrator. As well as viewing the entire history of reservations.

*** Functionality of an unregistered user:
- Change the interface language
- Authentication
- Check in
- View all hotel rooms page by page

* **Client**
The client, in turn, has three statuses: Active, Blocked, Deleted.
The guest who has passed the registration procedure is assigned the Client role and the Blocked status. A letter is sent to the client's e-mail specified during registration with a link by clicking on which the client's status changes to Active. If the client violates the rules for using the service, the administrator can block it, the status from Active will change to Blocked. If the client wants to delete his account on this resource, the status changes to Remote.

*** Functionality of the registered user(client):
- Change the interface language
- Making a reservation:
     - Creation of an application for a room according to certain non-interdependent requirements, such as the class of apartments, the number of beds in the room and the length of stay.
     - Pagination of rooms
- Refill
- View all bookings, including those made earlier
- Payment for the reservation (if confirmed by the administrator)
- Sorting bookings by several parameters
- View current balance and login
- Editing and deleting an account.

*** Administrator functionality:
- Change the interface language
- View all bookings (current, previously made by users)
- Sort bookings by date
- Confirmation of pending booking
- Rejection of any booking other than those already rejected earlier
- Adding a new room (s) to the hotel
- Changing the data of the room (room) in the hotel
- View all existing rooms (rooms) in the hotel
- View all existing users in the system
- Lock / Unlock Users
- Sorting users by specified parameters
