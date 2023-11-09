USE exchange_product;
SET NAMES 'utf8';

INSERT
IGNORE INTO role (id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT
IGNORE INTO account (id, username, password, phone, avatar, role_id, status)
VALUES (1, 'admin', '$2a$12$3L36iETAi4vNMRLeX7qBy.cN68/udOxM5IfKeVyikeC.qT5ej0IzG', '0999888777', 'https://genzrelax.com/wp-content/uploads/2022/03/anh-dai-dien-dep-4.jpg', 2, 'Đang hoạt động'),
 (2, 'user1', '$2a$12$3L36iETAi4vNMRLeX7qBy.cN68/udOxM5IfKeVyikeC.qT5ej0IzG', '0999888777', 'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Favatar-nhom-4-nguoi-26.jpg153f3a27-9d95-4194-9416-7642e6797aa0?alt=media&token=b46492af-4fda-4ac5-a08a-1935a45116cf', 1, 'Đang hoạt động');


INSERT INTO posts (title, category, description, requirement, address, avatar, created_at, status, account_id,
                   count_view)
VALUES ('Post 1', 'Sản phẩm muốn trao đổi', 'Description 1', 'Requirement 1', 'Hà Nội',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2F7a13fc80826419da94a42bb1b8e5f23f.jpg658c8001-544d-44a1-9d73-93f0b5690e38?alt=media&token=4f428b68-a296-40da-8c3d-41dab48cae64',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 2', 'Sản phẩm muốn trao đổi', 'Description 2', 'Requirement 2', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1671853906962-RV08WWNIS1LTNE453MOX/Artboard%2B2.jpg?format=750w',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 3', 'Sản phẩm cần tìm trao đổi', 'Description 3', 'Requirement 3', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 4', 'Sản phẩm cần tìm trao đổi', 'Description 4', 'Requirement 4', 'Hà Nội',
        'https://shop28decor.com/ct/uploads/2023/07/cac-kieu-chup-anh-san-pham-04-1024x1024-1.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       ('Post 5', 'Sản phẩm muốn trao đổi', 'Description 5', 'Requirement 5', 'Hà Nội',
        'https://mabustudio.com/wp-content/uploads/2020/02/chup-hinh-san-pham-tai-nha-7-scaled.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       ('Post 6', 'Sản phẩm cần tìm trao đổi', 'Description 6', 'Requirement 6', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 7', 'Sản phẩm cần tìm trao đổi', 'Description 7', 'Requirement 7', 'Hà Nội',
        'https://shop28decor.com/ct/uploads/2023/07/cac-kieu-chup-anh-san-pham-04-1024x1024-1.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       ('Post 8', 'Sản phẩm muốn trao đổi', 'Description 8', 'Requirement 8', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1671853906962-RV08WWNIS1LTNE453MOX/Artboard%2B2.jpg?format=750w',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 9', 'Sản phẩm muốn trao đổi', 'Description 9', 'Requirement 9', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 10', 'Sản phẩm muốn trao đổi', 'Description 10', 'Requirement 10', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1671853906962-RV08WWNIS1LTNE453MOX/Artboard%2B2.jpg?format=750w',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       ('Post 11', 'Sản phẩm muốn trao đổi', 'Description 11', 'Requirement 11', 'Hà Nội',
        'https://shop28decor.com/ct/uploads/2023/07/cac-kieu-chup-anh-san-pham-04-1024x1024-1.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       ('Post 12', 'Sản phẩm muốn trao đổi', 'Description 12', 'Requirement 12', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0);


