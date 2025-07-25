PGDMP  &    $    
            }            DocumentMIS    16.8    16.8 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19470    DocumentMIS    DATABASE     s   CREATE DATABASE "DocumentMIS" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en-US';
    DROP DATABASE "DocumentMIS";
                postgres    false            �            1259    19471    activity_log    TABLE     E  CREATE TABLE public.activity_log (
    id bigint NOT NULL,
    action character varying(255),
    content character varying(6000),
    department_name character varying(255),
    entity_name character varying(255),
    record_id bigint,
    "timestamp" timestamp(6) without time zone,
    user_name character varying(255)
);
     DROP TABLE public.activity_log;
       public         heap    postgres    false            �            1259    19476    activity_log_department_list    TABLE     �   CREATE TABLE public.activity_log_department_list (
    activity_log_id bigint NOT NULL,
    department_list_dep_id bigint NOT NULL
);
 0   DROP TABLE public.activity_log_department_list;
       public         heap    postgres    false            �            1259    19479    activity_log_id_seq    SEQUENCE     |   CREATE SEQUENCE public.activity_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.activity_log_id_seq;
       public          postgres    false    215            �           0    0    activity_log_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.activity_log_id_seq OWNED BY public.activity_log.id;
          public          postgres    false    217            �            1259    19480    appendant_docs    TABLE     �   CREATE TABLE public.appendant_docs (
    appendant_doc_id bigint NOT NULL,
    appendant_doc_download_url character varying(255),
    appendant_doc_name character varying(255),
    appendant_doc_path character varying(255),
    doc_id bigint
);
 "   DROP TABLE public.appendant_docs;
       public         heap    postgres    false            �            1259    19485 #   appendant_docs_appendant_doc_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appendant_docs_appendant_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.appendant_docs_appendant_doc_id_seq;
       public          postgres    false    218            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.appendant_docs_appendant_doc_id_seq OWNED BY public.appendant_docs.appendant_doc_id;
          public          postgres    false    219            �            1259    19486    backupdb    TABLE     �   CREATE TABLE public.backupdb (
    id bigint NOT NULL,
    backup_path character varying(255),
    created_at timestamp(6) without time zone,
    user_id bigint
);
    DROP TABLE public.backupdb;
       public         heap    postgres    false            �            1259    19489    backupdb_id_seq    SEQUENCE     x   CREATE SEQUENCE public.backupdb_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.backupdb_id_seq;
       public          postgres    false    220            �           0    0    backupdb_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.backupdb_id_seq OWNED BY public.backupdb.id;
          public          postgres    false    221            �            1259    19490    comment    TABLE     L  CREATE TABLE public.comment (
    comment_id bigint NOT NULL,
    comment_date_time timestamp(6) without time zone,
    comment_text character varying(255),
    status timestamp(6) without time zone,
    receiver_department bigint,
    document_id bigint,
    parent_comment_id bigint,
    send_doc_id bigint,
    user_id bigint
);
    DROP TABLE public.comment;
       public         heap    postgres    false            �            1259    19493    comment_comment_id_seq    SEQUENCE        CREATE SEQUENCE public.comment_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.comment_comment_id_seq;
       public          postgres    false    222            �           0    0    comment_comment_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.comment_comment_id_seq OWNED BY public.comment.comment_id;
          public          postgres    false    223            �            1259    19494 
   department    TABLE     �   CREATE TABLE public.department (
    dep_id bigint NOT NULL,
    dep_name character varying(255),
    description character varying(255),
    parent_dep_id bigint
);
    DROP TABLE public.department;
       public         heap    postgres    false            �            1259    19499    department_dep_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.department_dep_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.department_dep_id_seq;
       public          postgres    false    224            �           0    0    department_dep_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.department_dep_id_seq OWNED BY public.department.dep_id;
          public          postgres    false    225            �            1259    19500    doc_reference    TABLE     �   CREATE TABLE public.doc_reference (
    id bigint NOT NULL,
    ref_name character varying(255),
    description character varying(255),
    name character varying(255)
);
 !   DROP TABLE public.doc_reference;
       public         heap    postgres    false            �            1259    19505    doc_reference_id_seq    SEQUENCE     }   CREATE SEQUENCE public.doc_reference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.doc_reference_id_seq;
       public          postgres    false    226            �           0    0    doc_reference_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.doc_reference_id_seq OWNED BY public.doc_reference.id;
          public          postgres    false    227            �            1259    19506 
   doc_report    TABLE       CREATE TABLE public.doc_report (
    doc_report_id bigint NOT NULL,
    action character varying(4000),
    date timestamp(6) without time zone,
    doc_path character varying(255),
    doc_status smallint,
    download_url character varying(255),
    findings character varying(6000),
    report_title character varying(255),
    target_organ_response character varying(4000),
    send_doc_send_doc_id bigint,
    user_id bigint,
    CONSTRAINT doc_report_doc_status_check CHECK (((doc_status >= 0) AND (doc_status <= 4)))
);
    DROP TABLE public.doc_report;
       public         heap    postgres    false            �            1259    19512    doc_report_doc_report_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doc_report_doc_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.doc_report_doc_report_id_seq;
       public          postgres    false    228            �           0    0    doc_report_doc_report_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.doc_report_doc_report_id_seq OWNED BY public.doc_report.doc_report_id;
          public          postgres    false    229            �            1259    19513    document    TABLE     �  CREATE TABLE public.document (
    doc_id bigint NOT NULL,
    creation_date date,
    deadline date,
    description character varying(3000),
    doc_name character varying(255),
    doc_number character varying(255),
    doc_number2 character varying(255),
    doc_path character varying(255),
    doc_type character varying(255),
    download_url character varying(255),
    execution_type character varying(255),
    initial_date date,
    received_date date,
    reference_type character varying(255),
    second_date date,
    subject character varying(255),
    update_date date,
    department bigint,
    ref_id bigint,
    user_id bigint,
    CONSTRAINT document_doc_type_check CHECK (((doc_type)::text = ANY (ARRAY[('MAKTOOB'::character varying)::text, ('FARMAN'::character varying)::text, ('MUSAWWIBA'::character varying)::text, ('HIDAYAT'::character varying)::text, ('HUKAM'::character varying)::text, ('REPORT'::character varying)::text]))),
    CONSTRAINT document_execution_type_check CHECK (((execution_type)::text = ANY (ARRAY[('CONTINUOUS'::character varying)::text, ('INFORMATIVE'::character varying)::text, ('RESULT_BASE'::character varying)::text]))),
    CONSTRAINT document_reference_type_check CHECK (((reference_type)::text = ANY (ARRAY[('AMIR'::character varying)::text, ('RAYESULWOZARA'::character varying)::text, ('KABINA'::character varying)::text, ('FINANCE_KABINA'::character varying)::text, ('OTHER'::character varying)::text])))
);
    DROP TABLE public.document;
       public         heap    postgres    false            �            1259    19521    document_doc_id_seq    SEQUENCE     |   CREATE SEQUENCE public.document_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.document_doc_id_seq;
       public          postgres    false    230            �           0    0    document_doc_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.document_doc_id_seq OWNED BY public.document.doc_id;
          public          postgres    false    231            �            1259    19522    linking_doc    TABLE     �   CREATE TABLE public.linking_doc (
    id bigint NOT NULL,
    create_date date,
    first bigint,
    second bigint,
    department_dep_id bigint,
    user_id bigint
);
    DROP TABLE public.linking_doc;
       public         heap    postgres    false            �            1259    19525    linking_doc_id_seq    SEQUENCE     {   CREATE SEQUENCE public.linking_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.linking_doc_id_seq;
       public          postgres    false    232            �           0    0    linking_doc_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.linking_doc_id_seq OWNED BY public.linking_doc.id;
          public          postgres    false    233            �            1259    19526    notification    TABLE       CREATE TABLE public.notification (
    id bigint NOT NULL,
    content character varying(5000),
    created_at timestamp(6) without time zone,
    notification_type character varying(255),
    read_at timestamp(6) without time zone,
    recipient bigint
);
     DROP TABLE public.notification;
       public         heap    postgres    false            �            1259    19531    notification_id_seq    SEQUENCE     |   CREATE SEQUENCE public.notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.notification_id_seq;
       public          postgres    false    234            �           0    0    notification_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.notification_id_seq OWNED BY public.notification.id;
          public          postgres    false    235            �            1259    19532 
   permission    TABLE     �   CREATE TABLE public.permission (
    id bigint NOT NULL,
    dr_name character varying(255),
    en_name character varying(255),
    permission_name character varying(255),
    ps_name character varying(255)
);
    DROP TABLE public.permission;
       public         heap    postgres    false            �            1259    19537    permission_id_seq    SEQUENCE     z   CREATE SEQUENCE public.permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.permission_id_seq;
       public          postgres    false    236            �           0    0    permission_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.permission_id_seq OWNED BY public.permission.id;
          public          postgres    false    237            �            1259    19538    role_permissions    TABLE     i   CREATE TABLE public.role_permissions (
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);
 $   DROP TABLE public.role_permissions;
       public         heap    postgres    false            �            1259    19541    roles    TABLE     �   CREATE TABLE public.roles (
    id bigint NOT NULL,
    description character varying(255),
    role_name character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    19546    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    239            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    240            �            1259    19547    send_doc    TABLE     8  CREATE TABLE public.send_doc (
    send_doc_id bigint NOT NULL,
    action character varying(255),
    ancestor character varying(255),
    doc_status character varying(255),
    findings character varying(255),
    guide character varying(255),
    secret character varying(255),
    send_appendent_docs boolean DEFAULT true NOT NULL,
    send_orginal_doc boolean DEFAULT true NOT NULL,
    sending_date timestamp(6) without time zone,
    sending_status character varying(255),
    target_organ_response character varying(255),
    time_to_seen timestamp(6) without time zone,
    doc_id bigint,
    parent_send_doc_id bigint,
    receiver_department_dep_id bigint,
    sender_department_dep_id bigint,
    sender_userid_id bigint,
    CONSTRAINT send_doc_doc_status_check CHECK (((doc_status)::text = ANY (ARRAY[('TODO'::character varying)::text, ('IN_PROGRESS'::character varying)::text, ('DONE'::character varying)::text, ('IN_COMPLETE'::character varying)::text, ('VIOLATION'::character varying)::text]))),
    CONSTRAINT send_doc_secret_check CHECK (((secret)::text = ANY (ARRAY[('SECRET'::character varying)::text, ('NON_SECRET'::character varying)::text]))),
    CONSTRAINT send_doc_sending_status_check CHECK (((sending_status)::text = ANY (ARRAY[('PENDING'::character varying)::text, ('SEEN'::character varying)::text])))
);
    DROP TABLE public.send_doc;
       public         heap    postgres    false            �            1259    19557    send_doc_send_doc_id_seq    SEQUENCE     �   CREATE SEQUENCE public.send_doc_send_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.send_doc_send_doc_id_seq;
       public          postgres    false    241            �           0    0    send_doc_send_doc_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.send_doc_send_doc_id_seq OWNED BY public.send_doc.send_doc_id;
          public          postgres    false    242            �            1259    19558    token    TABLE     *  CREATE TABLE public.token (
    token_id bigint NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    token character varying(255),
    token_type character varying(255),
    user_id bigint,
    CONSTRAINT token_token_type_check CHECK (((token_type)::text = 'BEARER'::text))
);
    DROP TABLE public.token;
       public         heap    postgres    false            �            1259    19564    token_token_id_seq    SEQUENCE     {   CREATE SEQUENCE public.token_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.token_token_id_seq;
       public          postgres    false    243            �           0    0    token_token_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.token_token_id_seq OWNED BY public.token.token_id;
          public          postgres    false    244            �            1259    19565    user-department    TABLE     n   CREATE TABLE public."user-department" (
    "user-id" bigint NOT NULL,
    "department-id" bigint NOT NULL
);
 %   DROP TABLE public."user-department";
       public         heap    postgres    false            �            1259    19568 
   user-roles    TABLE     c   CREATE TABLE public."user-roles" (
    "user-id" bigint NOT NULL,
    "role-id" bigint NOT NULL
);
     DROP TABLE public."user-roles";
       public         heap    postgres    false            �            1259    19571    users    TABLE     �  CREATE TABLE public.users (
    id bigint NOT NULL,
    activate boolean NOT NULL,
    downloadurl character varying(255),
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    otp_code character varying(255),
    otp_expiration timestamp(6) without time zone,
    password character varying(255),
    phone_no character varying(255),
    "position" character varying(255),
    profile_path character varying(255),
    user_type character varying(255),
    CONSTRAINT users_user_type_check CHECK (((user_type)::text = ANY (ARRAY[('ADMIN'::character varying)::text, ('MEMBER_OF_COMMITTEE'::character varying)::text, ('MEMBER_OF_DEPARTMENT'::character varying)::text])))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    19577    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    247            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    248            �           2604    24798    activity_log id    DEFAULT     r   ALTER TABLE ONLY public.activity_log ALTER COLUMN id SET DEFAULT nextval('public.activity_log_id_seq'::regclass);
 >   ALTER TABLE public.activity_log ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    215            �           2604    24799    appendant_docs appendant_doc_id    DEFAULT     �   ALTER TABLE ONLY public.appendant_docs ALTER COLUMN appendant_doc_id SET DEFAULT nextval('public.appendant_docs_appendant_doc_id_seq'::regclass);
 N   ALTER TABLE public.appendant_docs ALTER COLUMN appendant_doc_id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    24800    backupdb id    DEFAULT     j   ALTER TABLE ONLY public.backupdb ALTER COLUMN id SET DEFAULT nextval('public.backupdb_id_seq'::regclass);
 :   ALTER TABLE public.backupdb ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    24801    comment comment_id    DEFAULT     x   ALTER TABLE ONLY public.comment ALTER COLUMN comment_id SET DEFAULT nextval('public.comment_comment_id_seq'::regclass);
 A   ALTER TABLE public.comment ALTER COLUMN comment_id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    24802    department dep_id    DEFAULT     v   ALTER TABLE ONLY public.department ALTER COLUMN dep_id SET DEFAULT nextval('public.department_dep_id_seq'::regclass);
 @   ALTER TABLE public.department ALTER COLUMN dep_id DROP DEFAULT;
       public          postgres    false    225    224            �           2604    24803    doc_reference id    DEFAULT     t   ALTER TABLE ONLY public.doc_reference ALTER COLUMN id SET DEFAULT nextval('public.doc_reference_id_seq'::regclass);
 ?   ALTER TABLE public.doc_reference ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226            �           2604    24804    doc_report doc_report_id    DEFAULT     �   ALTER TABLE ONLY public.doc_report ALTER COLUMN doc_report_id SET DEFAULT nextval('public.doc_report_doc_report_id_seq'::regclass);
 G   ALTER TABLE public.doc_report ALTER COLUMN doc_report_id DROP DEFAULT;
       public          postgres    false    229    228            �           2604    24805    document doc_id    DEFAULT     r   ALTER TABLE ONLY public.document ALTER COLUMN doc_id SET DEFAULT nextval('public.document_doc_id_seq'::regclass);
 >   ALTER TABLE public.document ALTER COLUMN doc_id DROP DEFAULT;
       public          postgres    false    231    230            �           2604    24806    linking_doc id    DEFAULT     p   ALTER TABLE ONLY public.linking_doc ALTER COLUMN id SET DEFAULT nextval('public.linking_doc_id_seq'::regclass);
 =   ALTER TABLE public.linking_doc ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232            �           2604    24807    notification id    DEFAULT     r   ALTER TABLE ONLY public.notification ALTER COLUMN id SET DEFAULT nextval('public.notification_id_seq'::regclass);
 >   ALTER TABLE public.notification ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    234            �           2604    24808    permission id    DEFAULT     n   ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);
 <   ALTER TABLE public.permission ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    236            �           2604    24809    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    240    239            �           2604    24810    send_doc send_doc_id    DEFAULT     |   ALTER TABLE ONLY public.send_doc ALTER COLUMN send_doc_id SET DEFAULT nextval('public.send_doc_send_doc_id_seq'::regclass);
 C   ALTER TABLE public.send_doc ALTER COLUMN send_doc_id DROP DEFAULT;
       public          postgres    false    242    241            �           2604    24811    token token_id    DEFAULT     p   ALTER TABLE ONLY public.token ALTER COLUMN token_id SET DEFAULT nextval('public.token_token_id_seq'::regclass);
 =   ALTER TABLE public.token ALTER COLUMN token_id DROP DEFAULT;
       public          postgres    false    244    243            �           2604    24812    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    248    247            �          0    19471    activity_log 
   TABLE DATA           |   COPY public.activity_log (id, action, content, department_name, entity_name, record_id, "timestamp", user_name) FROM stdin;
    public          postgres    false    215   ��       �          0    19476    activity_log_department_list 
   TABLE DATA           _   COPY public.activity_log_department_list (activity_log_id, department_list_dep_id) FROM stdin;
    public          postgres    false    216   ��       �          0    19480    appendant_docs 
   TABLE DATA           �   COPY public.appendant_docs (appendant_doc_id, appendant_doc_download_url, appendant_doc_name, appendant_doc_path, doc_id) FROM stdin;
    public          postgres    false    218   ��       �          0    19486    backupdb 
   TABLE DATA           H   COPY public.backupdb (id, backup_path, created_at, user_id) FROM stdin;
    public          postgres    false    220   P�       �          0    19490    comment 
   TABLE DATA           �   COPY public.comment (comment_id, comment_date_time, comment_text, status, receiver_department, document_id, parent_comment_id, send_doc_id, user_id) FROM stdin;
    public          postgres    false    222   m�       �          0    19494 
   department 
   TABLE DATA           R   COPY public.department (dep_id, dep_name, description, parent_dep_id) FROM stdin;
    public          postgres    false    224   +�       �          0    19500    doc_reference 
   TABLE DATA           H   COPY public.doc_reference (id, ref_name, description, name) FROM stdin;
    public          postgres    false    226   ~�       �          0    19506 
   doc_report 
   TABLE DATA           �   COPY public.doc_report (doc_report_id, action, date, doc_path, doc_status, download_url, findings, report_title, target_organ_response, send_doc_send_doc_id, user_id) FROM stdin;
    public          postgres    false    228   ��       �          0    19513    document 
   TABLE DATA             COPY public.document (doc_id, creation_date, deadline, description, doc_name, doc_number, doc_number2, doc_path, doc_type, download_url, execution_type, initial_date, received_date, reference_type, second_date, subject, update_date, department, ref_id, user_id) FROM stdin;
    public          postgres    false    230   ��       �          0    19522    linking_doc 
   TABLE DATA           a   COPY public.linking_doc (id, create_date, first, second, department_dep_id, user_id) FROM stdin;
    public          postgres    false    232   ��       �          0    19526    notification 
   TABLE DATA           f   COPY public.notification (id, content, created_at, notification_type, read_at, recipient) FROM stdin;
    public          postgres    false    234   �       �          0    19532 
   permission 
   TABLE DATA           T   COPY public.permission (id, dr_name, en_name, permission_name, ps_name) FROM stdin;
    public          postgres    false    236   K�       �          0    19538    role_permissions 
   TABLE DATA           B   COPY public.role_permissions (role_id, permission_id) FROM stdin;
    public          postgres    false    238   ��       �          0    19541    roles 
   TABLE DATA           ;   COPY public.roles (id, description, role_name) FROM stdin;
    public          postgres    false    239   I�       �          0    19547    send_doc 
   TABLE DATA           4  COPY public.send_doc (send_doc_id, action, ancestor, doc_status, findings, guide, secret, send_appendent_docs, send_orginal_doc, sending_date, sending_status, target_organ_response, time_to_seen, doc_id, parent_send_doc_id, receiver_department_dep_id, sender_department_dep_id, sender_userid_id) FROM stdin;
    public          postgres    false    241   ��       �          0    19558    token 
   TABLE DATA           W   COPY public.token (token_id, expired, revoked, token, token_type, user_id) FROM stdin;
    public          postgres    false    243   ��       �          0    19565    user-department 
   TABLE DATA           G   COPY public."user-department" ("user-id", "department-id") FROM stdin;
    public          postgres    false    245   �      �          0    19568 
   user-roles 
   TABLE DATA           <   COPY public."user-roles" ("user-id", "role-id") FROM stdin;
    public          postgres    false    246   �      �          0    19571    users 
   TABLE DATA           �   COPY public.users (id, activate, downloadurl, email, first_name, last_name, otp_code, otp_expiration, password, phone_no, "position", profile_path, user_type) FROM stdin;
    public          postgres    false    247         �           0    0    activity_log_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.activity_log_id_seq', 221, true);
          public          postgres    false    217            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.appendant_docs_appendant_doc_id_seq', 1, true);
          public          postgres    false    219            �           0    0    backupdb_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.backupdb_id_seq', 1, false);
          public          postgres    false    221            �           0    0    comment_comment_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.comment_comment_id_seq', 6, true);
          public          postgres    false    223            �           0    0    department_dep_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.department_dep_id_seq', 18, true);
          public          postgres    false    225            �           0    0    doc_reference_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doc_reference_id_seq', 1, false);
          public          postgres    false    227            �           0    0    doc_report_doc_report_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.doc_report_doc_report_id_seq', 4, true);
          public          postgres    false    229            �           0    0    document_doc_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.document_doc_id_seq', 10, true);
          public          postgres    false    231            �           0    0    linking_doc_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.linking_doc_id_seq', 1, false);
          public          postgres    false    233            �           0    0    notification_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.notification_id_seq', 11, true);
          public          postgres    false    235            �           0    0    permission_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.permission_id_seq', 45, true);
          public          postgres    false    237            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 3, true);
          public          postgres    false    240            �           0    0    send_doc_send_doc_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.send_doc_send_doc_id_seq', 27, true);
          public          postgres    false    242            �           0    0    token_token_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.token_token_id_seq', 197, true);
          public          postgres    false    244            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 5, true);
          public          postgres    false    248            �           2606    19594    activity_log activity_log_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.activity_log
    ADD CONSTRAINT activity_log_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.activity_log DROP CONSTRAINT activity_log_pkey;
       public            postgres    false    215            �           2606    19596 "   appendant_docs appendant_docs_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.appendant_docs
    ADD CONSTRAINT appendant_docs_pkey PRIMARY KEY (appendant_doc_id);
 L   ALTER TABLE ONLY public.appendant_docs DROP CONSTRAINT appendant_docs_pkey;
       public            postgres    false    218            �           2606    19598    backupdb backupdb_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.backupdb
    ADD CONSTRAINT backupdb_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.backupdb DROP CONSTRAINT backupdb_pkey;
       public            postgres    false    220            �           2606    19600    comment comment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (comment_id);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    222            �           2606    19602    department department_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (dep_id);
 D   ALTER TABLE ONLY public.department DROP CONSTRAINT department_pkey;
       public            postgres    false    224            �           2606    19604     doc_reference doc_reference_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.doc_reference
    ADD CONSTRAINT doc_reference_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.doc_reference DROP CONSTRAINT doc_reference_pkey;
       public            postgres    false    226            �           2606    19606    doc_report doc_report_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT doc_report_pkey PRIMARY KEY (doc_report_id);
 D   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT doc_report_pkey;
       public            postgres    false    228            �           2606    19608    document document_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (doc_id);
 @   ALTER TABLE ONLY public.document DROP CONSTRAINT document_pkey;
       public            postgres    false    230            �           2606    19610    linking_doc linking_doc_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT linking_doc_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT linking_doc_pkey;
       public            postgres    false    232            �           2606    19612    notification notification_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.notification DROP CONSTRAINT notification_pkey;
       public            postgres    false    234            �           2606    19614    permission permission_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permission DROP CONSTRAINT permission_pkey;
       public            postgres    false    236            �           2606    19616    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    239            �           2606    19618    send_doc send_doc_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT send_doc_pkey PRIMARY KEY (send_doc_id);
 @   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT send_doc_pkey;
       public            postgres    false    241            �           2606    19620    token token_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (token_id);
 :   ALTER TABLE ONLY public.token DROP CONSTRAINT token_pkey;
       public            postgres    false    243            �           2606    19622 "   users uk_6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ^   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    247            �           2606    19624 "   token uk_pddrhgwxnms2aceeku9s2ewy5 
   CONSTRAINT     ^   ALTER TABLE ONLY public.token
    ADD CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5 UNIQUE (token);
 L   ALTER TABLE ONLY public.token DROP CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5;
       public            postgres    false    243            �           2606    19626    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    247            �           2606    19627 (   notification fk1pllq3ww34eskdn6smt5vtbk7    FK CONSTRAINT     �   ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fk1pllq3ww34eskdn6smt5vtbk7 FOREIGN KEY (recipient) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.notification DROP CONSTRAINT fk1pllq3ww34eskdn6smt5vtbk7;
       public          postgres    false    234    4833    247            �           2606    19632 '   linking_doc fk1qj0kbbd2e7d3p3tbg3ht4x9d    FK CONSTRAINT     �   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT fk1qj0kbbd2e7d3p3tbg3ht4x9d FOREIGN KEY (department_dep_id) REFERENCES public.department(dep_id);
 Q   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT fk1qj0kbbd2e7d3p3tbg3ht4x9d;
       public          postgres    false    224    232    4809            �           2606    19637 #   comment fk29x8bst9rb3bcv6n4x9ypg3ip    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk29x8bst9rb3bcv6n4x9ypg3ip FOREIGN KEY (send_doc_id) REFERENCES public.send_doc(send_doc_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fk29x8bst9rb3bcv6n4x9ypg3ip;
       public          postgres    false    222    4825    241            �           2606    19642 +   user-department fk37f5twm0vgm8bl7fyqon1b1i8    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-department"
    ADD CONSTRAINT fk37f5twm0vgm8bl7fyqon1b1i8 FOREIGN KEY ("department-id") REFERENCES public.department(dep_id);
 W   ALTER TABLE ONLY public."user-department" DROP CONSTRAINT fk37f5twm0vgm8bl7fyqon1b1i8;
       public          postgres    false    245    4809    224            �           2606    19647 $   send_doc fk39y1mjmnkouaq4an05qtcrq3x    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fk39y1mjmnkouaq4an05qtcrq3x FOREIGN KEY (receiver_department_dep_id) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fk39y1mjmnkouaq4an05qtcrq3x;
       public          postgres    false    241    4809    224            �           2606    19652 $   send_doc fk3icem1p54fr3dm92499sw00b8    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fk3icem1p54fr3dm92499sw00b8 FOREIGN KEY (sender_userid_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fk3icem1p54fr3dm92499sw00b8;
       public          postgres    false    241    4833    247            �           2606    19657 &   user-roles fk5badaua3hwxpji2rnw94c3kui    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-roles"
    ADD CONSTRAINT fk5badaua3hwxpji2rnw94c3kui FOREIGN KEY ("user-id") REFERENCES public.users(id);
 R   ALTER TABLE ONLY public."user-roles" DROP CONSTRAINT fk5badaua3hwxpji2rnw94c3kui;
       public          postgres    false    246    247    4833            �           2606    19662 &   doc_report fk6i2kjuplgrh2mlrt79nb8yg0s    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT fk6i2kjuplgrh2mlrt79nb8yg0s FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT fk6i2kjuplgrh2mlrt79nb8yg0s;
       public          postgres    false    247    228    4833            �           2606    19667 $   backupdb fk78yur08i0ognkdchy7fi2emjx    FK CONSTRAINT     �   ALTER TABLE ONLY public.backupdb
    ADD CONSTRAINT fk78yur08i0ognkdchy7fi2emjx FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.backupdb DROP CONSTRAINT fk78yur08i0ognkdchy7fi2emjx;
       public          postgres    false    220    4833    247            �           2606    19672 8   activity_log_department_list fk7lpcggd1oxuawxlo1470li4f0    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity_log_department_list
    ADD CONSTRAINT fk7lpcggd1oxuawxlo1470li4f0 FOREIGN KEY (activity_log_id) REFERENCES public.activity_log(id);
 b   ALTER TABLE ONLY public.activity_log_department_list DROP CONSTRAINT fk7lpcggd1oxuawxlo1470li4f0;
       public          postgres    false    215    4801    216            �           2606    19677 $   send_doc fkbdv3116mkrd786kmf6m4y3kct    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkbdv3116mkrd786kmf6m4y3kct FOREIGN KEY (parent_send_doc_id) REFERENCES public.send_doc(send_doc_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkbdv3116mkrd786kmf6m4y3kct;
       public          postgres    false    241    241    4825            �           2606    19682 &   department fkbow3bfgirk8sm778ldfmtkhnj    FK CONSTRAINT     �   ALTER TABLE ONLY public.department
    ADD CONSTRAINT fkbow3bfgirk8sm778ldfmtkhnj FOREIGN KEY (parent_dep_id) REFERENCES public.department(dep_id);
 P   ALTER TABLE ONLY public.department DROP CONSTRAINT fkbow3bfgirk8sm778ldfmtkhnj;
       public          postgres    false    224    4809    224            �           2606    19687 #   comment fkcc7apjbw63fdti2fh7jjlo5n6    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkcc7apjbw63fdti2fh7jjlo5n6 FOREIGN KEY (receiver_department) REFERENCES public.department(dep_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkcc7apjbw63fdti2fh7jjlo5n6;
       public          postgres    false    224    4809    222            �           2606    19692 $   document fkcgv0id91kwa4apcarqcfen6ea    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkcgv0id91kwa4apcarqcfen6ea FOREIGN KEY (ref_id) REFERENCES public.doc_reference(id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkcgv0id91kwa4apcarqcfen6ea;
       public          postgres    false    230    4811    226            �           2606    19697 '   linking_doc fkg1awodmhfoqy66jrkdt78rv1h    FK CONSTRAINT     �   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT fkg1awodmhfoqy66jrkdt78rv1h FOREIGN KEY (user_id) REFERENCES public.users(id);
 Q   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT fkg1awodmhfoqy66jrkdt78rv1h;
       public          postgres    false    232    247    4833            �           2606    19702 ,   role_permissions fkh0v7u4w7mttcu81o8wegayr8e    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fkh0v7u4w7mttcu81o8wegayr8e FOREIGN KEY (permission_id) REFERENCES public.permission(id);
 V   ALTER TABLE ONLY public.role_permissions DROP CONSTRAINT fkh0v7u4w7mttcu81o8wegayr8e;
       public          postgres    false    238    4821    236            �           2606    19707 *   appendant_docs fkhbd56knga2fv9d2kbmsa9o8sr    FK CONSTRAINT     �   ALTER TABLE ONLY public.appendant_docs
    ADD CONSTRAINT fkhbd56knga2fv9d2kbmsa9o8sr FOREIGN KEY (doc_id) REFERENCES public.document(doc_id);
 T   ALTER TABLE ONLY public.appendant_docs DROP CONSTRAINT fkhbd56knga2fv9d2kbmsa9o8sr;
       public          postgres    false    230    218    4815            �           2606    19712 #   comment fkhvh0e2ybgg16bpu229a5teje7    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkhvh0e2ybgg16bpu229a5teje7 FOREIGN KEY (parent_comment_id) REFERENCES public.comment(comment_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkhvh0e2ybgg16bpu229a5teje7;
       public          postgres    false    222    4807    222            �           2606    19717 !   token fkj8rfw4x0wjjyibfqq566j4qng    FK CONSTRAINT     �   ALTER TABLE ONLY public.token
    ADD CONSTRAINT fkj8rfw4x0wjjyibfqq566j4qng FOREIGN KEY (user_id) REFERENCES public.users(id);
 K   ALTER TABLE ONLY public.token DROP CONSTRAINT fkj8rfw4x0wjjyibfqq566j4qng;
       public          postgres    false    247    243    4833            �           2606    19722 $   document fkm19xjdnh3l6aueyrpm1705t52    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkm19xjdnh3l6aueyrpm1705t52 FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkm19xjdnh3l6aueyrpm1705t52;
       public          postgres    false    230    247    4833            �           2606    19727 ,   role_permissions fkn5fotdgk8d1xvo8nav9uv3muc    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fkn5fotdgk8d1xvo8nav9uv3muc FOREIGN KEY (role_id) REFERENCES public.roles(id);
 V   ALTER TABLE ONLY public.role_permissions DROP CONSTRAINT fkn5fotdgk8d1xvo8nav9uv3muc;
       public          postgres    false    239    4823    238            �           2606    19732 $   document fkn8vwrnrx2lmvdmrgxr875w7wy    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkn8vwrnrx2lmvdmrgxr875w7wy FOREIGN KEY (department) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkn8vwrnrx2lmvdmrgxr875w7wy;
       public          postgres    false    230    224    4809            �           2606    19737 +   user-department fko7uo55j3hqk5rqvxmdnrw0scr    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-department"
    ADD CONSTRAINT fko7uo55j3hqk5rqvxmdnrw0scr FOREIGN KEY ("user-id") REFERENCES public.users(id);
 W   ALTER TABLE ONLY public."user-department" DROP CONSTRAINT fko7uo55j3hqk5rqvxmdnrw0scr;
       public          postgres    false    247    245    4833            �           2606    19742 8   activity_log_department_list fkodqlaujwsh5ww62509l2p30k8    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity_log_department_list
    ADD CONSTRAINT fkodqlaujwsh5ww62509l2p30k8 FOREIGN KEY (department_list_dep_id) REFERENCES public.department(dep_id);
 b   ALTER TABLE ONLY public.activity_log_department_list DROP CONSTRAINT fkodqlaujwsh5ww62509l2p30k8;
       public          postgres    false    224    4809    216            �           2606    19747 #   comment fkooerxu4oy4q0s0duwk3vtk74q    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkooerxu4oy4q0s0duwk3vtk74q FOREIGN KEY (document_id) REFERENCES public.document(doc_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkooerxu4oy4q0s0duwk3vtk74q;
       public          postgres    false    230    4815    222            �           2606    19752 #   send_doc fkpv5luxu01pay4dv1eoly5rjp    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkpv5luxu01pay4dv1eoly5rjp FOREIGN KEY (doc_id) REFERENCES public.document(doc_id);
 M   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkpv5luxu01pay4dv1eoly5rjp;
       public          postgres    false    4815    241    230            �           2606    19757 #   comment fkqm52p1v3o13hy268he0wcngr5    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkqm52p1v3o13hy268he0wcngr5 FOREIGN KEY (user_id) REFERENCES public.users(id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkqm52p1v3o13hy268he0wcngr5;
       public          postgres    false    222    4833    247            �           2606    19762 &   user-roles fkrbta1hppxxbup8ddfccar7622    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-roles"
    ADD CONSTRAINT fkrbta1hppxxbup8ddfccar7622 FOREIGN KEY ("role-id") REFERENCES public.roles(id);
 R   ALTER TABLE ONLY public."user-roles" DROP CONSTRAINT fkrbta1hppxxbup8ddfccar7622;
       public          postgres    false    4823    246    239            �           2606    19767 &   doc_report fksj0dswnyaxui1h59u626lblyk    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT fksj0dswnyaxui1h59u626lblyk FOREIGN KEY (send_doc_send_doc_id) REFERENCES public.send_doc(send_doc_id);
 P   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT fksj0dswnyaxui1h59u626lblyk;
       public          postgres    false    241    228    4825            �           2606    19772 $   send_doc fkssedv8p9lo5j02khcbc57aopc    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkssedv8p9lo5j02khcbc57aopc FOREIGN KEY (sender_department_dep_id) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkssedv8p9lo5j02khcbc57aopc;
       public          postgres    false    241    4809    224            �      x��=ۮGr��W�Y����7a� �*�6�<Ćr�3+197R����aI��_��}��8d;�e���&U�=Þ�gxzt��c��eXSU�u������t�pV^�o�LO��g��Gc|5?���٣ټ<�o��#>L��@�1�
%�rb��kb����p^��xpxt��.Ύ�[�'��`r�|�x��W������ϗ����'��lzv:_���kB��d��?��B��=�^����ˣ�z}x|�/��;O�O�%�stv��Iy:�sQ���(O�������򸜗��T�IǷ�
������t������;燳�'gG�O<�����!��~9�����q�{���`OO�}�~��^	��Og������E�~p�p������~x@���L��sR�����JD��/]@�@F㻋���zS}����w�ݹY�^�����J��X�@�o�5��g��Ӌ2Y�H7-���9�f���{��xz����f��:����i9~t'x�џ���ƍ:m1�`܄��Ad�����rZHK��{��y��#�q�`�b/E��(|�H�Ƌ����_.����G�nWr�%��E�������R۴s7a�pJ�"�*���҇�ؒ�c%���������}�x��|��}�x�x�l�񭇴��B��7�G�俿q��7n!��݌ׯ�����č�0�(�fF3^���}�I.���nX��-�k�^�����=�mb����6.H(���� � P�d�p+����+�2#G+ڪ]<F4�GD^"%��×_�g����^qdm
9� `D�ݜ����B^�	��Iؖ���_�O�κ~�Qz���}��yl�˦�	S�>����/�矖OD�?v�\�C��"u���Gʍg�'��d�xg����� 2��k���Js	=a9�9�Cյ����V]�ڭ�k3SV�[�
iw����J�+A�SmY��J,P�A�m�[���fkL1�
���*a���\Qlz�kM����J$�2CI�d�t���^���s6�ک�¢��`� �j�yJ����,�̑[�^��Rso�e�������`�����A+���_��W�-�0��K��]#�Y�����}����v5��G��3�/`�J<>#d-�\�;�р�өTغb�x��Q��mdd]42��`�$ޡ0�,t6�H�Wm]�`Ծ���bD�7PEJ�D�B�rx*[Qb[X;ᬰLY���9�RхV�H���d�����)�I4��1����~�>J�>J�FiVG$
tlGV]q[���y{y��{��|��A[�g)�W�`ɇ��0�J����I�k5�w6%����dev`3@�W&�"��X�k<�|����6����g�7Ud 5�B��j�p��_ŀ�����_}��s��D�>߆�]G
X!�TVVH��F�Hю�6B觡�Vy����i\C��6Ff�4-�u5��O�N�up!�%�8��r�X���yG4�@8��AU��{��.u�/<����ė���}����������:#�<�l�!�^��(�&4���@On�p.�"��%����Å�r�֠��j��<�3f��d�Y0��
Jj�>#Pf�?���k�B�)P��:��uy��)�n�p8{���8:��󳆗�F
��g{Ϋ�t�6��PE{j�������0:������K|��P<��hE�>�9�F����%G�+*��t�3�M>�Z��mbm��S���$����Rt����^�������t}�l%�W�]d(W0n�M�j���+=]h���Վ"� �@�5�8�yT��E�䯐%����~XA`����0|��y�
�����${ap�����=#�L�YJ���!�M�0�BI�����e��lzr~\v[5H�W�Q���U֊D��ަ��y8#Q��Z-�'P3����'�[���;ن�}�"���L�}-ߨm�CHe: �',fyJ ���La�0:�l]�[:/;-5xˣ���2ZK�>��V�Ö���|IJ����3��7��ûǱf��c���G������f[j�S �����w��T��B/�ʡX�/�nT�6�J�^FAz�NUL�#�ҏ�̸�$W� ��"e����V1�2�#��4?���'��LP�����z0��2^G˔(���>��N���Y49㖹�tP����ʙ�8Q�N��f^w1,��̨4~d��� ^�pS(�=H��׵}u�3Y���������%UF}��=,�[>^>���2��G�^"�>l��o��/��X-��������W�>#/��/�s��W�M_�-�O�����gT}A��B�|GB/Ƌ�)�T$��t������Z<��C�	uqak����]���N�Q��R��:_�㔬�� FM��E���M��{s;?�����Nb:�IQ�V�
i�!�엠!Zh�I�:�:�G��Fvd���G�1�@b�k*�x�f�I��Z6XB���y��U��u�R3B�6;%�*���L@� sx>�6��$F�C�Qk�����
�dT�T %�U�g��@-�JICT���!�ݔ	�n�X�i�k�<^;M��Uy��ivŮ�>X�6�n"���V�'�N<o+Ct�Q�.ý�v�Kc�l�&6��I:%��<=��<�)�/?��3�z���gVN�-�q�Z��A�������K��ހO��:��zW�vd�ѱ�f=q�%h�Dʺ��I�"#N�!�8e�
�9!���=XT�\S���!5�us�
	
�$��#YP
����./R��FM��VKc�&r�Q_�>gjɮG'�@�-����VR��Z�݁rkw`�ր�y��?����ܸ��_�Z�|���qؗ)���C�<�j�Ԑ�0�>�\���#���F��?�ͦ��mr��ȏ�dNr���e�u�-����p����dUa%;Z�	���p*�����ٿ���o{�'��W�P��Ej����&$�" B��z�e��б��鎞��\���eK�qaQ�SF��²��jm�
ιqh�;��p-%�Q����6Jƃ����������qK�����i*�oK�G�������NJ%-Xu��<;�WC7��(-��ofV�N$�h
E1(��۝2���NC�o��DxW�j��{w�4\��gz鼫^��B��Y�I�l�Cy/N�)�����y^D��15�{Ղ�!7a�#M{��	���5�)P;�m�?��\S�l]g(�KE���J�JP���@n�M��s�Wvu�����VZ��¡+�B�`��F��-;�^c{���r�KR�iV����Ev1i��(Dá�J�7D^�Ãҷ�
�ۨA�-C�?\�*#�}׃��@��J�Gfd+g�r.��)[�8�Ӄyo�jDcA5Rg���@�/�t�����k�4�Us-���Pt:�BiC|���@��6]J�����ue�?iI��ð�̅��ٺ��8�ġr������b����; ��v]��@�#@���2W�pFY.?��PVB�aⲇ;�wn@�|�L�������X���c@���r��]�Xc�E�qo�6χ��X~�[u^�k���I%�F����RշA�I!k<.�� r51�>���dx�CF W#\����IA���%j 퇶	ʥK�~X�H�m�ei��L`���=��`F�@����Q:�4�]{ycO�_�M[��#�	���Bvmb��Q�Iِ�]���L�pi��)���*�z��m�]���ւFw0a�.��w�]ä�׶��c��!	���l����^��O�0n�E�"Q�i��r�/����۠T�Ƚ�	0�Ge���~X�>����Cog��Rk:E5��ӵs��ٙ@W���z47��?�P��Q�^=����{պ/��o��z�Rd�i�d=�Yv�E�x��ʸ0!�緆W����D�������Y �VEй�j��
��h�Y���m}�F(C�]Ct�� E  ��wH:ߌ46��l�]Q�Ϭ��{\�_hxΓKcXQ�EH�h�����>��L�Zs��%�DZ�X>�,�2���59U=C}����R�3^��}[��`�X����D�ڍw"��z �AS1"�EЗ�9�V�3�)�_�ݕb��0����))�����,�wfv�0\�&�uE,י"��AY��v�J���0aI�;�S`S��0 �ms��s@��It�I�!=ML�?lz��[ಛg���C;�u�:�6?7�?_��/Fʴ͒�L��/}�kПK��K��J��Ì˷ͥԌ5'�g��d�aF�T_�#�\mPq��n�I͛O~���Ao�gW��ܟ_��_�������B��z�y��/��/�x�|���ݘ����T��e�Qʔ=r���1�Ϥn��
ʥL�'�{C?z�t��V1����#_�J��9��i[�|��c��i'}����j[ʲ6�G�vc��M��Y��W;.d�>��_�?���ۧv�1"��=r�7f����ev`l#�a՛-��3�/�o�5j��ǋ��=+��Y�SC�DeРH�����7s�hU����N+��ι]�<���"JK�r��\�5��)�3z8"�5,ֆ��	��۬�������#q-L�1�\;X�pݓM��MI�5��Ax��M5����g�q�۟@�x��J�誻{\^��N1*:e��x��]^ES�S��1���3�Up%�K���D>�u�Up��`��O\^sjW���*�#d-��c�e� me�py�|4�vs($��U��ȜYTå�]�cy˫l�xx��$�#A��,�yK.��i��G��ܱw.�'������[�4����
�W�<yC�a�������A)X��Up�I7���j��䭆�ih0�⣛D�P�
.�maA�6.�9���8܉f��� �\qvL��4*�A<o�ѿ�����mhI"O�\���B*)�8:�9g��W�ycV�)�"��l2&9J��6>�-O**ti�@+$(�����ފ��{�D�*x����Z����W���E��B�����z���<A+�5<�
ŧ��A`?,�]����^�      �   �  x�����0D�R0��@r���X��=)���ow(��ȱ�\����MQ�4`�8���x��B�Zcϥ�̅������� |S�����6|�1����C���o�3�����[>����+�ވ;�;�qG_����9�\^ ͭ��3�X�^3����
#�љk�̈́N"2�A�1r	I.%��-���R�:�ԣ�Иr�2�-,U�\�������D�i�:������ȵ�b\�Q�>�Rd���%� �4r��t2�$�>����HW�.#][@�e�M%���!�2I�ŬŬŬŬŬŬ��Zp6�`�M%���!�2I
'�������������nc�\��ul�������w�oVi��y�Dx�A\k�4��,M��)��SŹ�N�b��T�U���¸���,�5��w�A�"��G[T/��|���h���!��h'�����o�}c�k�X���7־�����Ͽ9��z?      �   X   x���;�  �Y�����.&�Op����w�No{����y��|I{�@�+�{ ���T��[�"	�4*j`v�<�2>�jg�y�$$+      �      x������ � �      �   �   x�U�A
�0�ur�\�!3ͤfv^�tY�*(�VzO .,��U��2ƶT��߽�A�A�d8S�l-�L{_8Gb^Uj�ܫ�z���
�!f`cιK������x��~�)@N�5x�D{���T�5�G�B���!���W�T:|�[Wǣ
��L`��4Rj)�-�B      �   C  x��S]R�@~ޜ�8j�����3�S�����e*L���M�r�X����>�|������<�Б4(�8�B�q4�j<?B��8�3~���2χ����i��(iN���7�&�D��AQN���u�yT�ƞ��\P\*҆�_J�2͇��i�E*�������&jF^g�K�����o{���7�BXY�8��%��,�lBx?�f��l�T+@k��fE�}%-?�E �nH�W�o#�a����ͽ.0�*,�(xm��AM�S���n8���|
��#4
�����O�vKr+�
'f�{K�$�:1�)z�;�v�Npxҿ2B| ?�mt�      �      x������ � �      �   ,  x����J1��٧�E�C���d6���/�ūP�ͮ����Bς��D�AQ��&ٷ1[,�ГB2d���ř@�ViP�$bAT����z�]FX��c!���]]���p6g��M!3N�x�Ӯ�(F�]57�̏�Nw�S�eS�d�.��Q7��ߍ��ᵿ��d�o�ӏ*����`�膆yJ\���Y�mv�M��%�����W�?��,���)Z$fq>���fq�.�a:��sM%(�i��kV���8�\[OfH!�4B�7)�^�;��Q�����!�C3��E�$�7N��R      �     x����n1���S�	Nm_��R�
B�����=6HEI�uR��G B�����З�3�ܚiKS'R�x|�����8�F1�Dqm�Q[鬆��Y���<	��������jm=��?p������׏&��4b��a�X���pp�I$H��	X:�ֶ^�5j�Y�����f�\�l�h�n�nO;�4���09���k�������s��y:J�8�0�!�`)��v�"��T��H?�&����<ά�a�9>��C?&_&1����pquV ڶε߶��� ؓz���&��s��lz�Q�y�@�c��IG����0(a�9f�����#�$]ŎOB1fW�`&g�o*Y��ֲ,i���><*���⻕,^Eř�f��9ҫX�#�	G�J�bjM�

+�9'��,.UCMB]�3�pH@�瀔!EX�;�W��a�)��5^��]48	�C/��dp?��K�����C:��NSO��(K�N�%JE���L|3��J($�pM��^z������˝�ӵ�J
�Z�1�~HRMύk�z�Տ[�zz%C�<3#o\��%R��|�Xi��'����b��z�.�O����g1bx�r< N��عL�MbM_�2��v��x�-��dY>�z�<�R�s.������SZM�ܞ�hʀ��uA�9R��Q8�?9�����B$x��l��p>8��D�bT�	:�T�؜0��oA,;d�$y�i�����%&
�0V�	J߁J� *��՗����R.�      �      x������ � �      �   +  x�՗�N�@���S�|&�~�wO��3��7$���C9T=P)���P��D}�uy��&1Nb��6�%�̬�������>���Q'������r#7F�2��&nlI�mE���$�Ü�$=�3�ю����w�{��{��^�lSp�-p�Af�{�t�$iD^r��z��2��8�5��;rSw��]:L����9r�==�ϯ0}�8�z����O5F�l�`Qf�\`B�"�����ϧ��1+�%�)35Vg��K #Ы�e�X{^����F��C����'�&��_QĨ-���Ш�Qb���a%�40�u����>�(��5�W5�"C)
(E9�R�M���˥�s�����"F,Vr������8_�Z���^2��Pfe��,c�,�	p�Z�)�C!��>d"[�1̤� pr\w�QEd*C�
�T���&`�*6a��6�K#*-�Vh�	!�,U(�[Q��G4����t�NЅ�TlAz �h��-e�*�u+ 9�~ׅ� W���8=�z�Wp�L�P���s��A��D�%
�]
�@rw���F�?��/1	��/^�k�:��4YC4{ݭ�	�j,M��H��*���6��g!6TS��X�V6�H�a�R����d����2��6s��3�N�׎0�k7[/:͸��r�yw#��l�A��s~� ]�
���w�Ws(��! �.�<]^���u7���8��O���s���7/VJ�������Vivz���!~*��3�%�C�:y.�,��P!3�4�(���뻸^�����      �   R  x��W�n7>�>�����ߵvR����R@�%FYD�
��:=��%��-@��HQm��l��<)�Lg�\��#�XK.���3�����ㅸ1g���S���b�J9{���f7¨�Cr�ĥ<_����ԯ{�P�u��|�	S���3L2�/�[
�%,��Ƃ��y�^�!D��`	G�~%�,Gj9��{9�_s&��H̲K��A+N�x?�:�ѹ���ݫoZ�+~���f7-G���W�ly�?bQ�j�Yo��^V?�'���9��Sx��㜗Q�{1��ɝs}�c}���=)�b�<��	{&����S� JyJ���n^c9[Ie�R}�`Qk���ݧ�����r��e7b/�$��[�����<pHk��X�%��ި�<e=:�	dt #1hh��p�Z=���j�䉸�ӂ�_D]�u��D��4sn-p}!��͗����H����|�_����ku:�N���b���C梹�iXe�ٴ�lX;�E�	!���G�T���/�uh�9������b�r"U�aM+�b��T%J��Zb]-j۞�8��.!�S��>^�� �^�=��l���ٷb	E�n��b���`�B̽��V��٠�$�Q���Y���
���hV�/��n�\�*}�\��%�%i+&u�eM����â9��_ߨ�/�tդ�<=�����vp���c�1xF��{���w��ZHK��ί�s�VȰ}`�L���w��0��*�B`��e�Q*ܹ{��XYجvg_P�;��y�%F�y�W]7�����?i��~q�>����i���Z�N ��ĘD]�1�8��X��u��c�JE9q�T9)\̭uH�{qGӋ<�8�����0M9g��C�d;�h��~v��ϔZ�FYX.���3��J��~�6��
� ����Ft*4/�Y4c�V�Mh�(�cL���c���j.>�4n̰�Q3����Y��\�;TF�ڦ�en�O�����;F�Q�lI2�2�,��toZ1�@M��.��`��M>���Lv�8L�i�M��'���Wp`|�~�UTq)���x����@;}���l��L�`���
 ��`M��1]>��z��Z>7E���d �k�e�=�N��gפ�F^���b��	JJ\3��kka����0��7�ˎ2r���@�C����B9�p =�����o�p��kb��[B��8���` ޤN?�(�;�u�6j�>�J�Ke4����0��Q�f����db/�͂@���dIP�e^[�c���If��sE!�.mc��b�p���<R7ʛ�5�k�֩ޯLW=�jf��	$SF��b��?�������      �   �   x���!�PLtk���%�בK�!�<v�`�29T3�n��8FH�ȱf9�$�F���8����4�FOA#��%^�$J1�P�W��'>!/"/"/�Qw�����`
U�BVO@�(�|=/�~�9?��[�      �   q   x�3�,��,V(��IU �E�)�i�y�)��̒�Ĝ��Ԣ�������b� �xG_O?.#Λ�7��Q��f ��ƪ���ĸ�9o�����ƛ�7�n�G�p��qqq G<Cn      �   �  x����j1����d�i�E��C�Cz
�^�@=�4M~��$uS
��9���t�rbj��ˮ�+�o<T'��O�FG��}���$~��x+���q^G��q��Q����%Ҏ��0 �j�wո�_��Z�e��C����G=h�4��]�=�@5��������2��7��1���q&���;�6��i�F �A���JG�Ā�â<#�2���/ͧ��	������8���ģģ�<g$v��$�)ʳR�ם<�x�,5���sey�������ey���'_[ �mx륒��O�P�@b7���8E6S2�4�j�#B���$2XVd���&���*�Yc��&���2��{�	i�,�3^9�>\	t������ǃѰ�=��X���|�����LH�Ll�Fҵ'�	VާˈV�����J�6�+��?���?Ly|�8�|@W;�ͮ6���l@Y{핢ʯ]�4�S�'�������L��[�$ri�J.d�����w�?Lى��c1ɺAB���M��^�m7�tZ����ڋLA/�2m.�k��k���d�};�} ���,ր�LH���B��*ϓ�[;@U�ś�cs)�O���������3���{q���E6&N ���C��k�>�Z׈<^����h*Z{���K���H�*�ף�I����S��      �      x���َ�ZҶ����-���l�`��~�b0�m����2wo�N�Vm�R�d������ưV����k/��'J"
� �r"����{Pw�{n��XBd��ʱ����Cp��;<Xx�����c�ԃ���m���r�4���G�<�p7�0I�7H�ª�l�q)����1OAs�b�:��<<��������Uٿh��8�_��س^�Ov]���M>��Q�5}����z��o*��c���SJ���c��m�����#+��K#{�59�t"Ɯ͘�uvj�ʼ���]p��w.{$�/v���jv�T����%x���{��@.x���BVp��w���U��*(� �݉U�Gn�S��� eֆ������εK"�1+S� �5�|B��;D{FI�
߭����*NI�+����㗏����Ѣ5\���!�'�41�Sڻi�8��������ZMh`��~�UF�a��H�gvCc!�X�i�3D����>�B��	ׂvaxpxHҹ��kl@҅�kH;�E3�P�:��+�<
��}��p����o���X~�G���Nz �I�c��T���b�Fk�\��ր������ K˯����?Y��&ˋF������c'4I^�Ӻ��Eo��N����UP��ր��/����OX��#!p��%¾���(�*~���t��Vmtp�F߹<�������j�W�)BI{F�]���U�z��T�7^JV�.��,`�=3����b�#�#���(�YaQ �Ԅ����C̨��b҈u%d��bǶp�D�-,����<����.�Q'��T��Q���<{�zPrC�P���/i���y��u�.�Z����� }$e#�.S�"Z8j,���K��U��=�Ga�wV��'�^.�������f���?�#��g{�?j��)Jn˗�]2
 N��$����qe�v`�\m~����1GX�q���H�,1�]~D��Ǐ=8fi�W���ܴ^%,�׉�_>?p?�� ��`h�4E�ĸETj�ݐ<�v����/��������m։�3�����N:q���1���'uq�k�i��dSOz���Co֗��u�w�Q9�O��P)�f�y�?n����^���Hć;jԞ�v8,��vk��a	������n3�w���<�p��U�):�3�m3^�(�R��S�v���j�������8��b+� ��k6�=���Ņ��G�(_�ޫ���vHf���S���G>����%~�
�6uon�-�Q�Zc���
��ҋ��63<,���>8u=B�&ww�U�ÑR#U�dTϞ��Z����������7|*�M�|����n�$w��x��#9�c`�e[Q��\�8��ml�����C���$6%F���pc3�L�y�<~��ĔU���g�nG޳��1j��x�GA����*�;~d���)�[+�9�ϰ�6lES$j�d����q	����ϥ�wا��)5S�ß��p8<���o���XO�>��H?%�b�>]�ߵґZ$��8�T�2���+	�RJ�b�X��F��Fh�.�����r/9�0�
@r��_��S%H�O��:�Ͳ�����2}ס��h�>�uwOmUG�˪o����n���c���򣀝S�aJl�ǖ�
����ڪ�_�@�.ι� 8f'>>�0���o��:9�$���D��r��~��^�.V��ː�����	w!�ċC��H�px�Jd��:9����	^���6�k#+D��B�#L�1���Do�(�$ƫ�U*��X����������=�2��=�\|�T?�K�Do�!ڋù~$91,�W�������G����61:�eg��� ]��*�'!
=��Jj,�W�Q��o}�o����0����/2�B��W����M_miP�RS�Q�z\�U��	>��Dfx���Rj��~<*M��7o�%jT���� �f6h_����Y�Z><¿�˾ứu��-�|�G�'�|ta��J�hu�%�zp����~�|8��;N_���q*��r[:ޗ�������D�znbz��|fK��t>����9h�y'�ur,g�3Ry��I�lA�)�#��@y�!o��YGm:iR��6S����u0�q̸��0r���8���O�E�F��v��fx��>�^���d�ܼ��X�����^Bx/�m3��X}��Ƣ������ ��R9�,�UR�g�%�"��pg���"ַ�zxnCFIJ�E�E�B։���)Xb?҃醰��D�B?����Dy�R��I����c����M ���'�A�������,4JBʂ�}ը�Ns�n"���}w�����Z#�:Aj�W[i���Yj��������3���$?A�˒2x��F8�{�k[H/;D[4m�u��� Q>�64$�T�x6z>�'���~u� {Յ'�|<%���dx���._L��������u~����m���{�QnXBĢkV�A��(�y������p3ԅ�W����f�>�6��H�ϼĝ�'J��A��g�)�.���$:��k�����J�2[~����]�ѕFCOw:��7��(�e�Aw�����ij��\��λw��zr�X[�m�9�Rj/�v7����4Y���`D���ćq� .��-s���	�d����߁f����u�JtJ��.����2ϵ2�C\��7�m;�(���'�S��:� ��|�sbֵ�q5`�ds8E\��j"<}�9Hn%bt�'��m�u,?������T��C4tJC=؁1����9y�V ����_R:��Y'��d����#%�����E3
�m�Z�*��ẋ����<���dPG\]�>��s`Gp
�,�K��t����&N!Ƌ0
��88j��8���`��M�[��-t���	^�?�EF�T��E܍�
.PR�1���)��6�B���+��zm�Ӭ��}#�:>����mfxh�ۤ�8�
��ۋo��NvD�6J�yZ���/y�� )�2]��/�-�~����1�a�*���DUX���W���r{y��A���(F�?��߄7�O�ZY�f�wt;{D����6�/ck��u7FX��n�<>�,9=�%�Je��}�>&xnn}�W�Y���d�,�#.�v<�uҝF@ݰ�t:�K�_��^f�����@���(��!� W�x��u�qv��0�-�u����Zަ���Sn|��Sn���|��Y�;�egqe担ga!������K��>w��:n3=;�e�]���>8������!P/ϗ�)���T�L#��aʜS�<�}����f��QY>��&x)E���%�7���@�ސ�k��>*?�I5\t�S�H�u��^f��LVQ`k�2��vݣ;?J�¶4�"r����*�u����>�?�ݣE�,��e�#Z�l��	>���.x[V�@�c!��:��7�{�?FXՄ<��EO��C�tN۔��R%e;�nq��	�k����6����?)�|+�yg�ǳ��wT�Y�E
��[f�ս���Bm�u҃o����~�m@>i�+h�n6J�k� �6:g�/��;GZ�7��� ,�6�:�����O����� KR�wc6��~�Y�qc����S �& ������x愯���O���������ڰa��	F"�!&:��<ݤX�#2�!�:�z:?u
~��	~'������)_�����k��~<z�|LV�^�<��Ώ��Y�7�t�y�n=r6��H;�3[D�y����}�j� zJy�,:f�:�:���g�S��?���y��E�"��~,?�"-(�I�>�rW�>,ޟ�TR#�(��N𻍼�XJ`ٻ�H�}Wa�F޺���`���m����pZX�ϥ2w~���|��6<�M;���n2�T�Xc�&����ه��(�۫-w����o�ϥ���ǊI�x�a#�tK8����,w�#TT�'S6�oyO���@��fB���� /�u��g�Ͼ�?�m�#,�Bp�    � �kê%Ql�>�ùI��a�x{@��� ��ImԹcj��r�z�B��)G�{t�l$a����h-��H�+�,rl�+)i�>r����v�<�7.��;�u�z ��A�h�~h��>�㣟�`���B�:���4?DX�ү[����AA�M��`A5��춱lYP6��9G������p�u"���\�Y�>�Ssx��M�Vv���Xzc�7����!ds�f��R)���X'�~��I����g]E:�&9̋޶,�(�����N�B��ex7�Djw�ې�TR��:?�O:����EK��w��@��D(�Kt �^U��ѫo���nr�;������6D�l�D��*��;�;��p>�72m��H�������������xo3����Og.]�ٝ�ݣ��H��(߶��H���,2�s+Z$f�:��o �5����K������Ӳ9����ёS/��GK�����n�)�o�p	��ۃ	�>�UN�Q?����3�4U�R'(� ח�Dp2�ۄ,,%fg,",�bQ)��~�4�o��>ٳ 5orSP�6my���N��|*MPi���/������k��;p��1��q���~$Eh�8c��B�r�B�j(�����cʟB��v[<Z!WSz}���r�l���j�$ ��A�2�^��eWxU[UU;/��49u& �ӯ#���3����sz�ÏKBt;]�[�U��*qS(����.����k9����m,��9��|˘s�X�V�]r[��2�`nY�r��
�z��Q=��[���։T��K?xN�H×����Tpb5=N���T�i�����<DKtj0E}�B��ڣs��=�N�v��d{ {d�l g��7�ku!�݆� g��m�?��Q]ү�\N���fz��l��ΥC���(*�J�-���K?g9w�M8�r����_Gs�饟��Skc���#�w'�|!���"b�����~FD��+�,��Y����#��^�e������y'p.�7Iѐ� 	��h�;�6{����)�Pk���{r���4�4�Ґ�'a1m�$r4������vp�ͳ�Cd��e��NE�j߻�_�v����w���wx>vR�t3[n*�+��#��<���,����0�����D?f����'Q�?�U!�K��,=Hԕo|�ď�6й���T�u&�fz�}q��=/�Hӛ|�!HP[�i�(�����*.��Z��2�����k?��S��ޫ��b.wO�CΥZ˙$aA*����d�Lv����\���ꌉ�>�w&��<�s4��]�x� \U:�Ԓ�rfh������핊7�UK�U�©�:����G��Z�S�>���v�ȃ��r�c�v[��o�CP���ۯҼ������/�l������0�PwѨ��96��7\��Z=�I0��a<���+��&�lz�:���?���)t�2�Zk�ވ䙿�c����W�V�q�F��d25_�~�X������^�ʭka�7��4��Q18�/g��H��$x���������$��;��eCSY�aY�X�/��֠�X�\2�\�}#�m~� �5 � ��~�k�i�ˀ~�����s����F���y5�Į?g���%���h�z�Qで6�A���ЯR�d��?N��1�VN%d�1����{�V|�o��=�F��%	��Nɂ1�K�����y���gKa�W��iO�yv�W�0�\�ҙ!In/�p�V��H��S�J~�MY"�?"�^b#xRL�	�r'�B��]2��m���Fۂ<H͗*���~�\g�D?V������gx:�[�.�7��4��U#�[p�JC��=��$�ex�_��\1'ZM���R�/�h^*`z� ��r�K�ӆ����D�w��5`�s-ƿ�uv�L��13ѧ�<��7u{<�	hc)��I��e��s0(���vكX�~��j��l��d{(τ�:��+^�%i��$��kM�ʲ�Α�͵#���/�'ۯ��=��bt����e�*�n�O�g��+Աr��p�S��� �vS
�/����5l?����D?~��AHB*�254A�嬴q�[��4��� ����'R=HK�ckѫ��$Ҕ��<�t	e��w��i�=j�eٳ�^���uIt>�{.�Z_�>j?��Gz�������\U)ʴ�m#X����Vb�N)���"��_l�N�K,}<���-{~`��D�%��.��;��+@�Qؐ�g�;`]� �����e���r��~�/|��F��pTL�K^�p�#�L6ey3��뾍�0�������m��x��]g��L�}��&zn�NL��nJ��D���4�~�\j�pʃ����������Ԝ�~�Z�v��ᴻc�(����r�*a�p6G풶�~�:s�3}�C�0�R'�ft"S3�c<��Y�Z��ԝj�#�5V��<�K�u�����,��y�Ċ�o�z�b���*VE��{�j�&v��*N�,��`
�ws����'ϱ�yM�v�7rP�]:o�(���BP����Dо��lb�� �N���?;"3���lA(��gx���`���5k$4�Edr�	��_��L{�>޶���C4�m�Nj;�oUӬ�-�%3�ߞ;����#ԝ�XT�=_���:�)2t�@)}?�����O�VБ���+���k����Of/��TL�$#+��a��X���O�F��ߢ�t4Ȭ0G��朎����9P��dn�0�;�-������2	(��K�Z!��,7u>����H��~�Cm.��?=��u\ώR׆M%�PH2�ƕ���=�`T�w�G�S?�K��9b��7�zd��1^Q��Cu��f�]@=�P�jMg����G�_��o����E�����x�\�U��{|����c.v�xʍ��_�W��_�>����~�痌u:0'R��ԗF�][�]�:��~^.��!9.,�)�1��E?�J��e<���\�vo_O���{���]�q�B֕ܧ�C M�*����]c�w�����=�u�kt��yj��=�\Q-����7�II`lUHI�bs,�y��c5�J��N���t��)C�(P�}�j{���E��:�],-[�_��R1ט����fd�O�iFfz����hO�EP����R]�a}�P<j���ԄT����#�g�1����PNt�s(Ƭ_O��#�ap,0��}'��*M�`p�-���_�֘ ������~���>����箢�'�V��������<d�Uv?�rsk,�W����?�O�V�az��
fiT��{�zo���TS�嶆��a�,c�?�Y�7���mJ_���6���I-�N^�|)��m�h����_���Mvҁ��]��[c��oz�g��em�I�[������q~Y$��������6�{��1�X��M*�bN�2)��䷋'��e��|x���x�=z>ۺ�at 7P��_l�J����'�1�%b�QnG��|Uy0}Bw��wJ��ǁs+��-A����H$�Ll�.=$}��Do�/yBעS���F��JW��;��aG��N>\.����X��?�����<ȹ޲Q���|�u��J�c��؝aQ+ùQ��P��j�ok�f�i��ԅ�6|M�G�i����P�y]�t�q��g�m1�E�e-����y�C��$�3�J��q� ���-�m%��j0T�l2�'oC�!�*��~Ŝ���L�`&�w'f�⡨`�=��c�����c��CN`�Ĉ|�;kט�m��U9��7�?ȏ5�	�h6�;H@�b6yQf� _�n����Q\>��������Y���S�WrM�J�Ѽ+W������V��ܺ�@cx[+�zm���1Q�_z]����Nm���j�D��V�� �� §w-���,ET\�����V�*�v��}B��s�'�62 9i��Z�ǵ;�Z�>��½6i��5��^ Z���UN�=�Y��{��rc��ϯ,4RRp�1�+� *Ż����sO�N.�B`�B�J�����|ӏ�s*�צ�����|��L�2Ք;:������|   ��Ɛ�*�[?�[1�~�"�y�P�秽Sظ|)����|)�Wic�[�s�X�M(��D���ߋ�3�p��ud����%���v�4��@�{�={�:x�C�Wќ��S��1��M[v�����'m��`�g)[H�:�}����'H��������1G�h���~��:�pC�1��&��E�)�{y�Ֆ㪠�P�=�����笱�d�G�a��y�)L���y�UPhFq�.����n�uqt8��v��/���JG�@�H�K��kR{^1����>����z[+����a������/S]�1���kS�X�O�#Vc.��`����X���ӳ�ؙa��a���vX�1���O?�?�ϓ6��*����^�5.XLyn�'��(�o�G��5@9ò����m�����5�t+#�Ș��Xn�2.I�����G��:wm߂R��V��x��W��>���+�RK((0�C�y7g�Y��>����D����}���ayj�XE�m{裶�moϵW���b��[?B��pV���y}����ě^�ׇ�2C&��m��ھ{[t�m{n^t\�@0{L�R��B���K{��Q�y�����1�Y��o�~��x߷;����v[F2��f1pnk��{����p	�ZN
Bٶ=,��{ �<'딟j+�C�<Y(�T4}�4����V�{ѹ8'��&+�=��Ԯ��O���m��:�6�&�ʼ|{x���Ǔ�S�p�+n� >����n������~��;�3�4O,�Yմ��[n��v@�S�������_k��1Wќ�� �����)��+1�1�BD��"nc�C��� ��x(/:�m�"-��Nm5��>O�h{c~X�C����j���#�Rs���p��ƾЫҚ���b����?\����� g-����]V��{y��I�׋sMו����CB�~9��J�9�����_y����]Y٦�����׼�IEzQ�|��H��C��EG���������H���      �      x�3�4�2�4�2Ҧ@ڈ ?F��� ��[      �   !   x�3�4�2�4�2�&@ڔӘːH�=... ���      �      x����n�P��k|�^x�� �?wU'�m��X�V�z�֋�G�e�����$����m�5$M���� e�[�sϴʣ�!d)�A1$Eq%ߝ���r�Mg^{��d���7jvI�A�7�F#hGe��0+l0a\+K��}�	�N��H��\��:X��Ot7���G�Z*��Pd>�I����\��	:yH��xO�?��O��/��=�#8��a}=Q�_�j8Z\�?YXe;��X	Ƒ$7�N���d���/�!]��dG��Q����&�U�芨�*7��!xD߻�ϒ>�>���	]��fא�^��6%�V���ѢΠ%Wk����I����L�O���K:�%��|J������Ӟ�1;��w�U����Mk�����훽�U�5|�ܱ�˕Ӱ��
ƃ���+�6L����T��\�N�������4�4J��]33r�����1E��_�C�͹޽��;�b��H�!7R<mU���Q�mO�+�>�[M��L� ���}�K�{"W�[sS*
 &S�     