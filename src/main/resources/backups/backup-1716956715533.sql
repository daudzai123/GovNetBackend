PGDMP         7                |            DMS_Update3    15.2    15.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    61134    DMS_Update3    DATABASE     �   CREATE DATABASE "DMS_Update3" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "DMS_Update3";
                postgres    false            �            1259    61136    activity_log    TABLE     2  CREATE TABLE public.activity_log (
    id bigint NOT NULL,
    action character varying(255),
    content text,
    department_name character varying(255),
    entity_name character varying(255),
    record_id bigint,
    "timestamp" timestamp(6) without time zone,
    user_name character varying(255)
);
     DROP TABLE public.activity_log;
       public         heap    postgres    false            �            1259    61144    activity_log_department_list    TABLE     �   CREATE TABLE public.activity_log_department_list (
    activity_log_id bigint NOT NULL,
    department_list_dep_id bigint NOT NULL
);
 0   DROP TABLE public.activity_log_department_list;
       public         heap    postgres    false            �            1259    61135    activity_log_id_seq    SEQUENCE     |   CREATE SEQUENCE public.activity_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.activity_log_id_seq;
       public          postgres    false    215            �           0    0    activity_log_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.activity_log_id_seq OWNED BY public.activity_log.id;
          public          postgres    false    214            �            1259    61148    appendant_docs    TABLE     �   CREATE TABLE public.appendant_docs (
    appendant_doc_id bigint NOT NULL,
    appendant_doc_download_url character varying(255),
    appendant_doc_name character varying(255),
    appendant_doc_path character varying(255),
    doc_id bigint
);
 "   DROP TABLE public.appendant_docs;
       public         heap    postgres    false            �            1259    61147 #   appendant_docs_appendant_doc_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appendant_docs_appendant_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.appendant_docs_appendant_doc_id_seq;
       public          postgres    false    218            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.appendant_docs_appendant_doc_id_seq OWNED BY public.appendant_docs.appendant_doc_id;
          public          postgres    false    217            �            1259    61157    backupdb    TABLE     �   CREATE TABLE public.backupdb (
    id bigint NOT NULL,
    backup_path character varying(255),
    created_at timestamp(6) without time zone,
    user_id bigint
);
    DROP TABLE public.backupdb;
       public         heap    postgres    false            �            1259    61156    backupdb_id_seq    SEQUENCE     x   CREATE SEQUENCE public.backupdb_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.backupdb_id_seq;
       public          postgres    false    220            �           0    0    backupdb_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.backupdb_id_seq OWNED BY public.backupdb.id;
          public          postgres    false    219            �            1259    61164    comment    TABLE     L  CREATE TABLE public.comment (
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
       public         heap    postgres    false            �            1259    61163    comment_comment_id_seq    SEQUENCE        CREATE SEQUENCE public.comment_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.comment_comment_id_seq;
       public          postgres    false    222            �           0    0    comment_comment_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.comment_comment_id_seq OWNED BY public.comment.comment_id;
          public          postgres    false    221            �            1259    61171 
   department    TABLE     �   CREATE TABLE public.department (
    dep_id bigint NOT NULL,
    dep_name character varying(255),
    description character varying(255),
    parent_dep_id bigint
);
    DROP TABLE public.department;
       public         heap    postgres    false            �            1259    61170    department_dep_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.department_dep_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.department_dep_id_seq;
       public          postgres    false    224            �           0    0    department_dep_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.department_dep_id_seq OWNED BY public.department.dep_id;
          public          postgres    false    223            �            1259    61180    doc_reference    TABLE     �   CREATE TABLE public.doc_reference (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255),
    ref_name character varying(255)
);
 !   DROP TABLE public.doc_reference;
       public         heap    postgres    false            �            1259    61179    doc_reference_id_seq    SEQUENCE     }   CREATE SEQUENCE public.doc_reference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.doc_reference_id_seq;
       public          postgres    false    226            �           0    0    doc_reference_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.doc_reference_id_seq OWNED BY public.doc_reference.id;
          public          postgres    false    225            �            1259    61189 
   doc_report    TABLE       CREATE TABLE public.doc_report (
    doc_report_id bigint NOT NULL,
    action character varying(255),
    date timestamp(6) without time zone,
    doc_path character varying(255),
    doc_status smallint,
    download_url character varying(255),
    findings character varying(255),
    report_title character varying(255),
    target_organ_response character varying(255),
    send_doc_send_doc_id bigint,
    user_id bigint,
    CONSTRAINT doc_report_doc_status_check CHECK (((doc_status >= 0) AND (doc_status <= 4)))
);
    DROP TABLE public.doc_report;
       public         heap    postgres    false            �            1259    61188    doc_report_doc_report_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doc_report_doc_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.doc_report_doc_report_id_seq;
       public          postgres    false    228            �           0    0    doc_report_doc_report_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.doc_report_doc_report_id_seq OWNED BY public.doc_report.doc_report_id;
          public          postgres    false    227            �            1259    61199    document    TABLE       CREATE TABLE public.document (
    doc_id bigint NOT NULL,
    creation_date date,
    deadline date,
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
    CONSTRAINT document_doc_type_check CHECK (((doc_type)::text = ANY ((ARRAY['MAKTOOB'::character varying, 'FARMAN'::character varying, 'MUSAWWIBA'::character varying, 'HIDAYAT'::character varying, 'HUKAM'::character varying, 'REPORT'::character varying])::text[]))),
    CONSTRAINT document_execution_type_check CHECK (((execution_type)::text = ANY ((ARRAY['CONTINUOUS'::character varying, 'INFORMATIVE'::character varying, 'RESULT_BASE'::character varying])::text[]))),
    CONSTRAINT document_reference_type_check CHECK (((reference_type)::text = ANY ((ARRAY['AMIR'::character varying, 'RAYESULWOZARA'::character varying, 'KABINA'::character varying, 'OTHER'::character varying])::text[])))
);
    DROP TABLE public.document;
       public         heap    postgres    false            �            1259    61198    document_doc_id_seq    SEQUENCE     |   CREATE SEQUENCE public.document_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.document_doc_id_seq;
       public          postgres    false    230            �           0    0    document_doc_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.document_doc_id_seq OWNED BY public.document.doc_id;
          public          postgres    false    229            �            1259    61211    linking_doc    TABLE     �   CREATE TABLE public.linking_doc (
    id bigint NOT NULL,
    create_date date,
    first bigint,
    second bigint,
    department_dep_id bigint,
    user_id bigint
);
    DROP TABLE public.linking_doc;
       public         heap    postgres    false            �            1259    61210    linking_doc_id_seq    SEQUENCE     {   CREATE SEQUENCE public.linking_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.linking_doc_id_seq;
       public          postgres    false    232            �           0    0    linking_doc_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.linking_doc_id_seq OWNED BY public.linking_doc.id;
          public          postgres    false    231            �            1259    61218    notification    TABLE       CREATE TABLE public.notification (
    id bigint NOT NULL,
    content character varying(1000),
    created_at timestamp(6) without time zone,
    notification_type character varying(255),
    read_at timestamp(6) without time zone,
    recipient bigint
);
     DROP TABLE public.notification;
       public         heap    postgres    false            �            1259    61217    notification_id_seq    SEQUENCE     |   CREATE SEQUENCE public.notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.notification_id_seq;
       public          postgres    false    234            �           0    0    notification_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.notification_id_seq OWNED BY public.notification.id;
          public          postgres    false    233            �            1259    61227 
   permission    TABLE     �   CREATE TABLE public.permission (
    id bigint NOT NULL,
    dr_name character varying(255),
    en_name character varying(255),
    permission_name character varying(255),
    ps_name character varying(255)
);
    DROP TABLE public.permission;
       public         heap    postgres    false            �            1259    61226    permission_id_seq    SEQUENCE     z   CREATE SEQUENCE public.permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.permission_id_seq;
       public          postgres    false    236            �           0    0    permission_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.permission_id_seq OWNED BY public.permission.id;
          public          postgres    false    235            �            1259    61235    role_permissions    TABLE     i   CREATE TABLE public.role_permissions (
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);
 $   DROP TABLE public.role_permissions;
       public         heap    postgres    false            �            1259    61239    roles    TABLE     �   CREATE TABLE public.roles (
    id bigint NOT NULL,
    description character varying(255),
    role_name character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    61238    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    239            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    238            �            1259    61248    send_doc    TABLE       CREATE TABLE public.send_doc (
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
    CONSTRAINT send_doc_doc_status_check CHECK (((doc_status)::text = ANY ((ARRAY['TODO'::character varying, 'IN_PROGRESS'::character varying, 'DONE'::character varying, 'IN_COMPLETE'::character varying, 'VIOLATION'::character varying])::text[]))),
    CONSTRAINT send_doc_secret_check CHECK (((secret)::text = ANY ((ARRAY['SECRET'::character varying, 'NON_SECRET'::character varying])::text[]))),
    CONSTRAINT send_doc_sending_status_check CHECK (((sending_status)::text = ANY ((ARRAY['PENDING'::character varying, 'SEEN'::character varying])::text[])))
);
    DROP TABLE public.send_doc;
       public         heap    postgres    false            �            1259    61247    send_doc_send_doc_id_seq    SEQUENCE     �   CREATE SEQUENCE public.send_doc_send_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.send_doc_send_doc_id_seq;
       public          postgres    false    241            �           0    0    send_doc_send_doc_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.send_doc_send_doc_id_seq OWNED BY public.send_doc.send_doc_id;
          public          postgres    false    240            �            1259    61262    token    TABLE     *  CREATE TABLE public.token (
    token_id bigint NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    token character varying(255),
    token_type character varying(255),
    user_id bigint,
    CONSTRAINT token_token_type_check CHECK (((token_type)::text = 'BEARER'::text))
);
    DROP TABLE public.token;
       public         heap    postgres    false            �            1259    61261    token_token_id_seq    SEQUENCE     {   CREATE SEQUENCE public.token_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.token_token_id_seq;
       public          postgres    false    243            �           0    0    token_token_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.token_token_id_seq OWNED BY public.token.token_id;
          public          postgres    false    242            �            1259    61271    user-department    TABLE     n   CREATE TABLE public."user-department" (
    "user-id" bigint NOT NULL,
    "department-id" bigint NOT NULL
);
 %   DROP TABLE public."user-department";
       public         heap    postgres    false            �            1259    61274 
   user-roles    TABLE     c   CREATE TABLE public."user-roles" (
    "user-id" bigint NOT NULL,
    "role-id" bigint NOT NULL
);
     DROP TABLE public."user-roles";
       public         heap    postgres    false            �            1259    61278    users    TABLE     �  CREATE TABLE public.users (
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
    CONSTRAINT users_user_type_check CHECK (((user_type)::text = ANY ((ARRAY['ADMIN'::character varying, 'MEMBER_OF_COMMITTEE'::character varying, 'MEMBER_OF_DEPARTMENT'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    61277    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    247            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    246            �           2604    61139    activity_log id    DEFAULT     r   ALTER TABLE ONLY public.activity_log ALTER COLUMN id SET DEFAULT nextval('public.activity_log_id_seq'::regclass);
 >   ALTER TABLE public.activity_log ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            �           2604    61151    appendant_docs appendant_doc_id    DEFAULT     �   ALTER TABLE ONLY public.appendant_docs ALTER COLUMN appendant_doc_id SET DEFAULT nextval('public.appendant_docs_appendant_doc_id_seq'::regclass);
 N   ALTER TABLE public.appendant_docs ALTER COLUMN appendant_doc_id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    61160    backupdb id    DEFAULT     j   ALTER TABLE ONLY public.backupdb ALTER COLUMN id SET DEFAULT nextval('public.backupdb_id_seq'::regclass);
 :   ALTER TABLE public.backupdb ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    61167    comment comment_id    DEFAULT     x   ALTER TABLE ONLY public.comment ALTER COLUMN comment_id SET DEFAULT nextval('public.comment_comment_id_seq'::regclass);
 A   ALTER TABLE public.comment ALTER COLUMN comment_id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    61174    department dep_id    DEFAULT     v   ALTER TABLE ONLY public.department ALTER COLUMN dep_id SET DEFAULT nextval('public.department_dep_id_seq'::regclass);
 @   ALTER TABLE public.department ALTER COLUMN dep_id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    61183    doc_reference id    DEFAULT     t   ALTER TABLE ONLY public.doc_reference ALTER COLUMN id SET DEFAULT nextval('public.doc_reference_id_seq'::regclass);
 ?   ALTER TABLE public.doc_reference ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    61192    doc_report doc_report_id    DEFAULT     �   ALTER TABLE ONLY public.doc_report ALTER COLUMN doc_report_id SET DEFAULT nextval('public.doc_report_doc_report_id_seq'::regclass);
 G   ALTER TABLE public.doc_report ALTER COLUMN doc_report_id DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    61202    document doc_id    DEFAULT     r   ALTER TABLE ONLY public.document ALTER COLUMN doc_id SET DEFAULT nextval('public.document_doc_id_seq'::regclass);
 >   ALTER TABLE public.document ALTER COLUMN doc_id DROP DEFAULT;
       public          postgres    false    229    230    230            �           2604    61214    linking_doc id    DEFAULT     p   ALTER TABLE ONLY public.linking_doc ALTER COLUMN id SET DEFAULT nextval('public.linking_doc_id_seq'::regclass);
 =   ALTER TABLE public.linking_doc ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    61221    notification id    DEFAULT     r   ALTER TABLE ONLY public.notification ALTER COLUMN id SET DEFAULT nextval('public.notification_id_seq'::regclass);
 >   ALTER TABLE public.notification ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    234    234            �           2604    61230    permission id    DEFAULT     n   ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);
 <   ALTER TABLE public.permission ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    236    235    236            �           2604    61242    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    238    239    239            �           2604    61251    send_doc send_doc_id    DEFAULT     |   ALTER TABLE ONLY public.send_doc ALTER COLUMN send_doc_id SET DEFAULT nextval('public.send_doc_send_doc_id_seq'::regclass);
 C   ALTER TABLE public.send_doc ALTER COLUMN send_doc_id DROP DEFAULT;
       public          postgres    false    241    240    241            �           2604    61265    token token_id    DEFAULT     p   ALTER TABLE ONLY public.token ALTER COLUMN token_id SET DEFAULT nextval('public.token_token_id_seq'::regclass);
 =   ALTER TABLE public.token ALTER COLUMN token_id DROP DEFAULT;
       public          postgres    false    242    243    243            �           2604    61281    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    247    246    247            �          0    61136    activity_log 
   TABLE DATA           |   COPY public.activity_log (id, action, content, department_name, entity_name, record_id, "timestamp", user_name) FROM stdin;
    public          postgres    false    215   +�       �          0    61144    activity_log_department_list 
   TABLE DATA           _   COPY public.activity_log_department_list (activity_log_id, department_list_dep_id) FROM stdin;
    public          postgres    false    216   ~�       �          0    61148    appendant_docs 
   TABLE DATA           �   COPY public.appendant_docs (appendant_doc_id, appendant_doc_download_url, appendant_doc_name, appendant_doc_path, doc_id) FROM stdin;
    public          postgres    false    218   U�       �          0    61157    backupdb 
   TABLE DATA           H   COPY public.backupdb (id, backup_path, created_at, user_id) FROM stdin;
    public          postgres    false    220   ��       �          0    61164    comment 
   TABLE DATA           �   COPY public.comment (comment_id, comment_date_time, comment_text, status, receiver_department, document_id, parent_comment_id, send_doc_id, user_id) FROM stdin;
    public          postgres    false    222   ��       �          0    61171 
   department 
   TABLE DATA           R   COPY public.department (dep_id, dep_name, description, parent_dep_id) FROM stdin;
    public          postgres    false    224   u�       �          0    61180    doc_reference 
   TABLE DATA           H   COPY public.doc_reference (id, description, name, ref_name) FROM stdin;
    public          postgres    false    226   b�       �          0    61189 
   doc_report 
   TABLE DATA           �   COPY public.doc_report (doc_report_id, action, date, doc_path, doc_status, download_url, findings, report_title, target_organ_response, send_doc_send_doc_id, user_id) FROM stdin;
    public          postgres    false    228   ��       �          0    61199    document 
   TABLE DATA             COPY public.document (doc_id, creation_date, deadline, doc_name, doc_number, doc_number2, doc_path, doc_type, download_url, execution_type, initial_date, received_date, reference_type, second_date, subject, update_date, department, ref_id, user_id) FROM stdin;
    public          postgres    false    230   �       �          0    61211    linking_doc 
   TABLE DATA           a   COPY public.linking_doc (id, create_date, first, second, department_dep_id, user_id) FROM stdin;
    public          postgres    false    232   �       �          0    61218    notification 
   TABLE DATA           f   COPY public.notification (id, content, created_at, notification_type, read_at, recipient) FROM stdin;
    public          postgres    false    234   R�       �          0    61227 
   permission 
   TABLE DATA           T   COPY public.permission (id, dr_name, en_name, permission_name, ps_name) FROM stdin;
    public          postgres    false    236   ��       �          0    61235    role_permissions 
   TABLE DATA           B   COPY public.role_permissions (role_id, permission_id) FROM stdin;
    public          postgres    false    237   ��       �          0    61239    roles 
   TABLE DATA           ;   COPY public.roles (id, description, role_name) FROM stdin;
    public          postgres    false    239   �       �          0    61248    send_doc 
   TABLE DATA           4  COPY public.send_doc (send_doc_id, action, ancestor, doc_status, findings, guide, secret, send_appendent_docs, send_orginal_doc, sending_date, sending_status, target_organ_response, time_to_seen, doc_id, parent_send_doc_id, receiver_department_dep_id, sender_department_dep_id, sender_userid_id) FROM stdin;
    public          postgres    false    241   ��       �          0    61262    token 
   TABLE DATA           W   COPY public.token (token_id, expired, revoked, token, token_type, user_id) FROM stdin;
    public          postgres    false    243   ��       �          0    61271    user-department 
   TABLE DATA           G   COPY public."user-department" ("user-id", "department-id") FROM stdin;
    public          postgres    false    244   M�       �          0    61274 
   user-roles 
   TABLE DATA           <   COPY public."user-roles" ("user-id", "role-id") FROM stdin;
    public          postgres    false    245   y�       �          0    61278    users 
   TABLE DATA           �   COPY public.users (id, activate, downloadurl, email, first_name, last_name, otp_code, otp_expiration, password, phone_no, "position", profile_path, user_type) FROM stdin;
    public          postgres    false    247   ��       �           0    0    activity_log_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.activity_log_id_seq', 68, true);
          public          postgres    false    214            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.appendant_docs_appendant_doc_id_seq', 12, true);
          public          postgres    false    217            �           0    0    backupdb_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.backupdb_id_seq', 1, true);
          public          postgres    false    219            �           0    0    comment_comment_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.comment_comment_id_seq', 3, true);
          public          postgres    false    221            �           0    0    department_dep_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.department_dep_id_seq', 6, true);
          public          postgres    false    223            �           0    0    doc_reference_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.doc_reference_id_seq', 8, true);
          public          postgres    false    225            �           0    0    doc_report_doc_report_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.doc_report_doc_report_id_seq', 2, true);
          public          postgres    false    227            �           0    0    document_doc_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.document_doc_id_seq', 6, true);
          public          postgres    false    229            �           0    0    linking_doc_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.linking_doc_id_seq', 6, true);
          public          postgres    false    231            �           0    0    notification_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.notification_id_seq', 7, true);
          public          postgres    false    233            �           0    0    permission_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.permission_id_seq', 35, true);
          public          postgres    false    235            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 2, true);
          public          postgres    false    238            �           0    0    send_doc_send_doc_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.send_doc_send_doc_id_seq', 8, true);
          public          postgres    false    240            �           0    0    token_token_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.token_token_id_seq', 52, true);
          public          postgres    false    242            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 4, true);
          public          postgres    false    246            �           2606    61143    activity_log activity_log_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.activity_log
    ADD CONSTRAINT activity_log_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.activity_log DROP CONSTRAINT activity_log_pkey;
       public            postgres    false    215            �           2606    61155 "   appendant_docs appendant_docs_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.appendant_docs
    ADD CONSTRAINT appendant_docs_pkey PRIMARY KEY (appendant_doc_id);
 L   ALTER TABLE ONLY public.appendant_docs DROP CONSTRAINT appendant_docs_pkey;
       public            postgres    false    218            �           2606    61162    backupdb backupdb_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.backupdb
    ADD CONSTRAINT backupdb_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.backupdb DROP CONSTRAINT backupdb_pkey;
       public            postgres    false    220            �           2606    61169    comment comment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (comment_id);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    222            �           2606    61178    department department_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (dep_id);
 D   ALTER TABLE ONLY public.department DROP CONSTRAINT department_pkey;
       public            postgres    false    224            �           2606    61187     doc_reference doc_reference_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.doc_reference
    ADD CONSTRAINT doc_reference_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.doc_reference DROP CONSTRAINT doc_reference_pkey;
       public            postgres    false    226            �           2606    61197    doc_report doc_report_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT doc_report_pkey PRIMARY KEY (doc_report_id);
 D   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT doc_report_pkey;
       public            postgres    false    228            �           2606    61209    document document_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (doc_id);
 @   ALTER TABLE ONLY public.document DROP CONSTRAINT document_pkey;
       public            postgres    false    230            �           2606    61216    linking_doc linking_doc_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT linking_doc_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT linking_doc_pkey;
       public            postgres    false    232            �           2606    61225    notification notification_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.notification DROP CONSTRAINT notification_pkey;
       public            postgres    false    234            �           2606    61234    permission permission_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permission DROP CONSTRAINT permission_pkey;
       public            postgres    false    236            �           2606    61246    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    239            �           2606    61260    send_doc send_doc_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT send_doc_pkey PRIMARY KEY (send_doc_id);
 @   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT send_doc_pkey;
       public            postgres    false    241            �           2606    61270    token token_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (token_id);
 :   ALTER TABLE ONLY public.token DROP CONSTRAINT token_pkey;
       public            postgres    false    243            �           2606    61290 "   users uk_6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ^   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    247            �           2606    61288 "   token uk_pddrhgwxnms2aceeku9s2ewy5 
   CONSTRAINT     ^   ALTER TABLE ONLY public.token
    ADD CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5 UNIQUE (token);
 L   ALTER TABLE ONLY public.token DROP CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5;
       public            postgres    false    243            �           2606    61286    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    247                       2606    61376 (   notification fk1pllq3ww34eskdn6smt5vtbk7    FK CONSTRAINT     �   ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fk1pllq3ww34eskdn6smt5vtbk7 FOREIGN KEY (recipient) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.notification DROP CONSTRAINT fk1pllq3ww34eskdn6smt5vtbk7;
       public          postgres    false    3318    234    247                       2606    61366 '   linking_doc fk1qj0kbbd2e7d3p3tbg3ht4x9d    FK CONSTRAINT     �   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT fk1qj0kbbd2e7d3p3tbg3ht4x9d FOREIGN KEY (department_dep_id) REFERENCES public.department(dep_id);
 Q   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT fk1qj0kbbd2e7d3p3tbg3ht4x9d;
       public          postgres    false    3294    224    232            �           2606    61326 #   comment fk29x8bst9rb3bcv6n4x9ypg3ip    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk29x8bst9rb3bcv6n4x9ypg3ip FOREIGN KEY (send_doc_id) REFERENCES public.send_doc(send_doc_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fk29x8bst9rb3bcv6n4x9ypg3ip;
       public          postgres    false    241    3310    222                       2606    61421 +   user-department fk37f5twm0vgm8bl7fyqon1b1i8    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-department"
    ADD CONSTRAINT fk37f5twm0vgm8bl7fyqon1b1i8 FOREIGN KEY ("department-id") REFERENCES public.department(dep_id);
 W   ALTER TABLE ONLY public."user-department" DROP CONSTRAINT fk37f5twm0vgm8bl7fyqon1b1i8;
       public          postgres    false    3294    224    244                       2606    61401 $   send_doc fk39y1mjmnkouaq4an05qtcrq3x    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fk39y1mjmnkouaq4an05qtcrq3x FOREIGN KEY (receiver_department_dep_id) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fk39y1mjmnkouaq4an05qtcrq3x;
       public          postgres    false    3294    224    241                       2606    61411 $   send_doc fk3icem1p54fr3dm92499sw00b8    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fk3icem1p54fr3dm92499sw00b8 FOREIGN KEY (sender_userid_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fk3icem1p54fr3dm92499sw00b8;
       public          postgres    false    241    247    3318                       2606    61436 &   user-roles fk5badaua3hwxpji2rnw94c3kui    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-roles"
    ADD CONSTRAINT fk5badaua3hwxpji2rnw94c3kui FOREIGN KEY ("user-id") REFERENCES public.users(id);
 R   ALTER TABLE ONLY public."user-roles" DROP CONSTRAINT fk5badaua3hwxpji2rnw94c3kui;
       public          postgres    false    245    247    3318                       2606    61346 &   doc_report fk6i2kjuplgrh2mlrt79nb8yg0s    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT fk6i2kjuplgrh2mlrt79nb8yg0s FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT fk6i2kjuplgrh2mlrt79nb8yg0s;
       public          postgres    false    247    228    3318            �           2606    61306 $   backupdb fk78yur08i0ognkdchy7fi2emjx    FK CONSTRAINT     �   ALTER TABLE ONLY public.backupdb
    ADD CONSTRAINT fk78yur08i0ognkdchy7fi2emjx FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.backupdb DROP CONSTRAINT fk78yur08i0ognkdchy7fi2emjx;
       public          postgres    false    220    3318    247            �           2606    61296 8   activity_log_department_list fk7lpcggd1oxuawxlo1470li4f0    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity_log_department_list
    ADD CONSTRAINT fk7lpcggd1oxuawxlo1470li4f0 FOREIGN KEY (activity_log_id) REFERENCES public.activity_log(id);
 b   ALTER TABLE ONLY public.activity_log_department_list DROP CONSTRAINT fk7lpcggd1oxuawxlo1470li4f0;
       public          postgres    false    3286    216    215                       2606    61396 $   send_doc fkbdv3116mkrd786kmf6m4y3kct    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkbdv3116mkrd786kmf6m4y3kct FOREIGN KEY (parent_send_doc_id) REFERENCES public.send_doc(send_doc_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkbdv3116mkrd786kmf6m4y3kct;
       public          postgres    false    3310    241    241                        2606    61336 &   department fkbow3bfgirk8sm778ldfmtkhnj    FK CONSTRAINT     �   ALTER TABLE ONLY public.department
    ADD CONSTRAINT fkbow3bfgirk8sm778ldfmtkhnj FOREIGN KEY (parent_dep_id) REFERENCES public.department(dep_id);
 P   ALTER TABLE ONLY public.department DROP CONSTRAINT fkbow3bfgirk8sm778ldfmtkhnj;
       public          postgres    false    3294    224    224            �           2606    61311 #   comment fkcc7apjbw63fdti2fh7jjlo5n6    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkcc7apjbw63fdti2fh7jjlo5n6 FOREIGN KEY (receiver_department) REFERENCES public.department(dep_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkcc7apjbw63fdti2fh7jjlo5n6;
       public          postgres    false    224    222    3294                       2606    61356 $   document fkcgv0id91kwa4apcarqcfen6ea    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkcgv0id91kwa4apcarqcfen6ea FOREIGN KEY (ref_id) REFERENCES public.doc_reference(id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkcgv0id91kwa4apcarqcfen6ea;
       public          postgres    false    226    230    3296                       2606    61371 '   linking_doc fkg1awodmhfoqy66jrkdt78rv1h    FK CONSTRAINT     �   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT fkg1awodmhfoqy66jrkdt78rv1h FOREIGN KEY (user_id) REFERENCES public.users(id);
 Q   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT fkg1awodmhfoqy66jrkdt78rv1h;
       public          postgres    false    3318    232    247            	           2606    61381 ,   role_permissions fkh0v7u4w7mttcu81o8wegayr8e    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fkh0v7u4w7mttcu81o8wegayr8e FOREIGN KEY (permission_id) REFERENCES public.permission(id);
 V   ALTER TABLE ONLY public.role_permissions DROP CONSTRAINT fkh0v7u4w7mttcu81o8wegayr8e;
       public          postgres    false    237    236    3306            �           2606    61301 *   appendant_docs fkhbd56knga2fv9d2kbmsa9o8sr    FK CONSTRAINT     �   ALTER TABLE ONLY public.appendant_docs
    ADD CONSTRAINT fkhbd56knga2fv9d2kbmsa9o8sr FOREIGN KEY (doc_id) REFERENCES public.document(doc_id);
 T   ALTER TABLE ONLY public.appendant_docs DROP CONSTRAINT fkhbd56knga2fv9d2kbmsa9o8sr;
       public          postgres    false    218    3300    230            �           2606    61321 #   comment fkhvh0e2ybgg16bpu229a5teje7    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkhvh0e2ybgg16bpu229a5teje7 FOREIGN KEY (parent_comment_id) REFERENCES public.comment(comment_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkhvh0e2ybgg16bpu229a5teje7;
       public          postgres    false    3292    222    222                       2606    61416 !   token fkj8rfw4x0wjjyibfqq566j4qng    FK CONSTRAINT     �   ALTER TABLE ONLY public.token
    ADD CONSTRAINT fkj8rfw4x0wjjyibfqq566j4qng FOREIGN KEY (user_id) REFERENCES public.users(id);
 K   ALTER TABLE ONLY public.token DROP CONSTRAINT fkj8rfw4x0wjjyibfqq566j4qng;
       public          postgres    false    243    3318    247                       2606    61361 $   document fkm19xjdnh3l6aueyrpm1705t52    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkm19xjdnh3l6aueyrpm1705t52 FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkm19xjdnh3l6aueyrpm1705t52;
       public          postgres    false    3318    230    247            
           2606    61386 ,   role_permissions fkn5fotdgk8d1xvo8nav9uv3muc    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fkn5fotdgk8d1xvo8nav9uv3muc FOREIGN KEY (role_id) REFERENCES public.roles(id);
 V   ALTER TABLE ONLY public.role_permissions DROP CONSTRAINT fkn5fotdgk8d1xvo8nav9uv3muc;
       public          postgres    false    239    237    3308                       2606    61351 $   document fkn8vwrnrx2lmvdmrgxr875w7wy    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkn8vwrnrx2lmvdmrgxr875w7wy FOREIGN KEY (department) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkn8vwrnrx2lmvdmrgxr875w7wy;
       public          postgres    false    230    224    3294                       2606    61426 +   user-department fko7uo55j3hqk5rqvxmdnrw0scr    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-department"
    ADD CONSTRAINT fko7uo55j3hqk5rqvxmdnrw0scr FOREIGN KEY ("user-id") REFERENCES public.users(id);
 W   ALTER TABLE ONLY public."user-department" DROP CONSTRAINT fko7uo55j3hqk5rqvxmdnrw0scr;
       public          postgres    false    244    3318    247            �           2606    61291 8   activity_log_department_list fkodqlaujwsh5ww62509l2p30k8    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity_log_department_list
    ADD CONSTRAINT fkodqlaujwsh5ww62509l2p30k8 FOREIGN KEY (department_list_dep_id) REFERENCES public.department(dep_id);
 b   ALTER TABLE ONLY public.activity_log_department_list DROP CONSTRAINT fkodqlaujwsh5ww62509l2p30k8;
       public          postgres    false    224    3294    216            �           2606    61316 #   comment fkooerxu4oy4q0s0duwk3vtk74q    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkooerxu4oy4q0s0duwk3vtk74q FOREIGN KEY (document_id) REFERENCES public.document(doc_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkooerxu4oy4q0s0duwk3vtk74q;
       public          postgres    false    222    230    3300                       2606    61391 #   send_doc fkpv5luxu01pay4dv1eoly5rjp    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkpv5luxu01pay4dv1eoly5rjp FOREIGN KEY (doc_id) REFERENCES public.document(doc_id);
 M   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkpv5luxu01pay4dv1eoly5rjp;
       public          postgres    false    230    3300    241            �           2606    61331 #   comment fkqm52p1v3o13hy268he0wcngr5    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkqm52p1v3o13hy268he0wcngr5 FOREIGN KEY (user_id) REFERENCES public.users(id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkqm52p1v3o13hy268he0wcngr5;
       public          postgres    false    222    3318    247                       2606    61431 &   user-roles fkrbta1hppxxbup8ddfccar7622    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-roles"
    ADD CONSTRAINT fkrbta1hppxxbup8ddfccar7622 FOREIGN KEY ("role-id") REFERENCES public.roles(id);
 R   ALTER TABLE ONLY public."user-roles" DROP CONSTRAINT fkrbta1hppxxbup8ddfccar7622;
       public          postgres    false    239    3308    245                       2606    61341 &   doc_report fksj0dswnyaxui1h59u626lblyk    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT fksj0dswnyaxui1h59u626lblyk FOREIGN KEY (send_doc_send_doc_id) REFERENCES public.send_doc(send_doc_id);
 P   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT fksj0dswnyaxui1h59u626lblyk;
       public          postgres    false    241    3310    228                       2606    61406 $   send_doc fkssedv8p9lo5j02khcbc57aopc    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkssedv8p9lo5j02khcbc57aopc FOREIGN KEY (sender_department_dep_id) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkssedv8p9lo5j02khcbc57aopc;
       public          postgres    false    224    3294    241            �   C  x��Z�n�F]�_1�:%���p���8EҠ�&h��˒ RI� ���E�#H��H�E��wH����P��eK��HlX"g�{�ι�4�� ���Ý�vxw7�~0H��nڠ�)��E�\��yJs)e���A�E��g���g�SpM�l>[:��k�aO�	����Ey�!T)��K���|�3���̗���z�����>�L��L�hg�hP��|,=��	f:?s�jtr�v�	~?�ѻ�����w�Wh��}���ގ�7z�����s�3cnm�>�~sr�/���-��FOQ��G�0�(�HAA�W�=q��S�q��`0o��E�֫@��܉&��
�MX����o���G9W���W�ڒ��ZI��e�G�
����-T>1�Č�
U�1쇠jM�5-�6��T��)�u�c�>*}�q�E�}r����<�	�,�Qe]j#]���r�FQj*p�uyfN�B����H�Så)�5u��e�{L�eI-ų����<�pF��7/��浜K�vnA�|J����d��6_�|�D��U�zS��|�=�sƞ�p�q��Vt�#X�����h73�P���>�G��7�ݾ�º+6([8���4����"Q>o���l���~c�M\�_�a���I��� ����)���D��Т�"LR*`[�~���0��<}�H�$A-sD��Ho`��@?��F��HUdLI��~o0I���%�V�.yf&��8�8�9=���r��r%o���9����1�X�!�`]�/�?��	��j4h
y�������$�~ՏG1|�u����V1ϛ�A�$����M������Bp�ο?�.�-���{wo�lm߸sk�9���h�����I:�����:����l�'�L\�	����R��+�r+�J�:��֓8z
w��ᰟ�	�[s1�t�(@Yؖ�m&�+�������E����Yqׂ�D(y���Q�,�˥.�%�k����E�y�G��ZT` ��Z�j��Rǲ�����RcE�T�K��8�-eך���%���Eލ�8ɈY������ǣλ@�Np�h�� �6_�Wˊ�նTLk(n�~ˣ`%\�
��B��lŢ�k�<H���l���,��;n�1��s��+&�=
����d�Cy�9^��f:o�aW�L5
�5M�Z�h/Dݶ�ˊ��F�(���Y�-��t7��s�s�q��)�#f�O����ƣ�2�qL_PJZ�}}������og���QUT�yp��n}��Na)�+�����R���`ņÒb�`��3T��SS6ˑ���:�T�b_r�Ȝ���\؞�L� ZP�t��֬ �Q0�4�MsV���U%�~���a���;�y��K��Z �5�>s�C��g>����Kg!c�(���N�Ж�u=�2�W{4�^�~}8{W�P����\�T�m�Z�y�΄�N�t
[�j�s�i�8�ƞ,A�T]�TQ�vg���y5�b�mj��g���%bײl�*
,�[���Ǡ�6�4�����-P�$B�z��ѻ�&�,����KO	��ߢ^�eԥ:���5�a!����Hؑ��=\�sR
Po���İ��3c�d/�$ь^.��6��n*��aF�-GR�˓��
����K�f���R��%Q#Ieʄ�sc<�X�1.����h7�1�8�O{�A\�����7@A��_�����l�ٮ������*����VX��ЛCc���Ϝ�:>��c[��?�W��K��5���@g��{[�9Ma��Rڍ����iݿy������b�dк��r�`��V!�i�H�G���iOK%��ĕ��B#�ڹ�B�U���n�̎(��h_�������7� XR5iu�C[���{B1����:w�8���A� �ك!�+�/ܗ��y�P�qwp�q�?��V��M]�U�7�D��cw2��#{! �&u�k+���/�{���s���;�h��ܨY]6U�w��{�{����ƔA����>�Q�0�lZ�L�B6���֭�M�����I�1n������$�c�      �   �   x�=�ˍA�M0+�y����Ǳ�ܒ�@���SQ������ƞt���c����x;��b"q�l�&��"�D������@=~�\u1QHi�36
_᫧-F�o9@�}�0
4 ��Lkf4'h�����r��_`����� �;�;l:X����,ZD�h-�ߊ>�'�� ���!�_�e��r�ŷ���������@�      �   #  x��UM��F=�~�^z�3��{X E�R4=p>�8�eW��������B��Q����G��޽���a�����J�I�)����鷏�O�?�u����������ˇ?���A�d�*��N+c� G��ho�5:�%&�(_#�`4���w�gѦ��ӳ�����3�fX�O�� O�]����[���m��T۱���i���z䡶�{֭)!�lQ��c��&�Q�1N~(�#,���*��+a���r�M���o��<pc��9�YOH���:b��.{��������J���A(=R�	b��w�d�9b�#ы$.{�������Z��O����Ϲ�H��d�
15�D5P��Z���^*��b��T0.�:���ۨ7,Y�:7K�����ly�IZ���6�Hole?���L�n�x)c�8ˌZ�Ę[���#Y�"݂}�¥$�ùIlv�nǄJ��\�@�"�(c�K�Z1����0�N���4�����^�ϟC���x=��������#��q?���Qr?U�܆VK�p�5k��Y�Y5C���U�4%��z��� ޿�}���`ԅYBҵM�CNc[�y�Xl��,p&�Bi[��F�䳻�t�5�����8#˱�7:�+i��s5��XZtޥܪ梬x�*%�u)��1�3b��L.x4�F7���޴hipӡ�+�*>�fIY#nJ�`��e`�Wk�}��
.����u���R0�3nO��td1�i��Vڀ�|�b�&�H%�%L��F$'\C/^�U7�%�*�5�i܌���9|��C|��Ŕl�ת�����      �   D   x����0�:��l�9��̒(� !��mߎ��pK�ֵ�}�fj�,��9�I�gzM!��G      �   �   x�]̻�0���z
g �{�`�	(-%t��`:�Lc�2�J���#�k�Z8�6I4�������e�e)�>o�}.��kMꀠ�;�hA�1Y;��v�)lw{
� h�I|�N�QS��?�R�k�+�      �   �   x�}��m�0߼*X�&��pn&�(�*���;@�I{R3٣^�=�;����~��	�F��=��ZM�C"�������N��;.&f\���<uKAm�����`Q��׶���R���&�l��m�Om�&p�߸�^�}Lc���&;Zt�y��T��㣭��e�h�dkcn��z�����WH������Ճ����ec�$ᶨm��?�2      �      x�3�����?. #���=... ���      �   o  x��T�N�0���Ȃ���'���R�Jb�J[�"v�V�,�Dh��L^1 �X"� �uw���N��f�X7��q�W���&�]{<��/�ָ�����~�uH��K��>����m	�����x���>�����.��	O������L��s��2 �� *�e�
e��E�+(��Jd�4��Y!**+������\���C��P���6���Izvr;Mr��)��k�����&!x�mHq���"Ժ��b����ON�Y�C"�6F��ϩ��7W!���b4w��R��������Vi�R�.��CM�e��vB����Bp���VD|p�w'<����C+����Y�y�M����3$Q���
      �   �  x����n�0ǯӧ�\���?b_v�@�кj7��4N�`k&Ƈv	bc�GQ5��!$n�{N��e[%$���'�����|�� H���`})��#;/�AJ6������\OG:	S����:Cef�$��
�L�Tc"r`�2V�J�Io��̹��`+�����=`�,�w~����Y�-��7�BX7�К�,�����A��Ł?.Hq�ψ�SR���c�M����I]H���!�r�P�i�ƻ�%���>As�e����Y�`7�H�L�$h�2*8j�ʨ2D�i�\R9���{�{i��0Ku�(ޏ���i��v��l5�ou�S��]��:ފI�_�x�$~.���r�^f6o^��m�f3�<#�V5=D,����!�Z��R����8t}
I�U��,	mB.A�f&��2��3l�]�V1J�E��Z���x+ޮ�2!�&9E������?'�g?��B���SqQ|*K�ǅv�5v����X% ���&aL���S�h"b�C3fm�NK<Z�J��m��u�[5~��x.��l�XV$�!+}�V���G��^��?a�^�ha���F0�[=GIjAf�ь9K�M�xY��Y�R'� �x���
����,,w�����u+�2*��'Y�_�����}��xzp�a�z3��rsg�Qm0@�e"�}a��8����J~_��e��R�\u
k-ʏ��*����R�֝MCn��bg�����K�^���i�4֛�F�7��
      �   ?   x�U��	  C��tŦ�Ӹ�>����U��S3���'O�w���Sփ����-�H�CD      �   "  x�Ֆ�N�@���S�����ji�aѰ��2ɐ��v;R+Ģ!Y�� �>�8y����\JHi%��}��̜���	N���r��<��m��>���*�y�
h�>�[!;�g��w�W�]ǫ�e��u��
�,|�DT��4�*�U��9����D�-�`|���3����Dq��yZ�_�+�IN���ArY_�5Q������0Y��5� [D�$]�LC&��?�ea�((9e��������p|7��ؗ��k�W�d��@����FB#���eܙP�:�B�a�0��i�,V@Po�[��ɟK;�{{[6Ӽ�iѯ��
ߛ%�.�nKۥ�4{vKHy���i��*#�]6d?_-q��Y��whԟI<���d�RK�DM���|]�F�i*y���}N�j˩ ��P�����g���qlݛp��V��+�d�D�%����s�9� ,�.M�'Hy7|V�֞g��ě~=A��5�u����k;u��+��	WR�7����M�E��g�h.o��dĮS"����l-HLw�2AK��?�;��A�\LK�,�Y�M(�%�8�Ev�vWs���}`�r��~Հ���0�9��DCL��?q"yΉ�rߚ	�lC$��by�	IN��Ե�{�����/����E����a�%3��~q��������Eh"<�W���Ŗ��X�$�,�W^�Hu������W�:��$�I*�~�L�����KϐJ�b*&���4P'�$�X#�)�ܾ���~;�а      �     x��V�n�F>/�b��0E�q�uz(��@����,nS��(�R��Yv��z�#�VT;E~�]�e:3�����lj����}�c�۹~�z%�j;7�s������R�3V�u��@�"u?�]|�ˆb�G������捹4Ө�ml�ұ+���/
��	�cxt��8J(���\��\�o���k�f�	�ԣ�/��h?wW.��rΔ�+3�3��cO�j�����%͔nM�CA��ꅙZ��_;y���@6�������dd$+��O��
�ZSb�/ߤ�q�+�Q
�b�����)+O�����K(�9��%�r��1��;58�2w�u{P�����7o��(�c�e5?��`�L����EA�h{&�g�����k :�13[}t#��v'M����d��� ��+�7U��"��FY>9=Gbr�bpŉ���Ћ2�ʧ�9�b�No�,�}�Ɯ"�ҥ���8���0!X�%|�*X
��X(��#C|����>~,�֒���K�g/E?{�V���@�k�ޜY�>S}���)��lEy#�(>r(�=T���a�%�m�����w��&CO͹ڼ���������Kxy���ם�o��<��sJk�CxY�emݫ5pc8X�Z4,XS7y+v�4.^؎qA�v���9�FZL )�֛���mo�(y�`��D�$��-ZwY�\�{c�0#�8��cf+/�Dk�y�c�@W��r5�*"�v&r��y��X�|)��l[�����ڦ���� 3W�ʗ�����Jm[OX�j6�PP�&�Ȁ6���b�aK�#'�Pƥ��5��Zp����uL_�&�Q�����o��\��������Z���T�v�e�rƙ��5�ޒ�{_�7@�k(�	�`?DH�d8B�r����Ox���yR�X%3I�d���V7�'� :(����S��W(7~���O���i�`T�9%���2�UIҗ�=�Z������T����cիv]� ��ލ�Y�ޡNɁ(ȝό ����j����8��_EQ��4��      �   S   x����0�jk��I��%��Aq���p���ٙ��u `�'Da	Mx�3�f�j�h%F5.W{�w�TM\��j�������      �   �   x�e�K
�@D�3��zA�p-��b���&��#*�0���5�e�\���Guu�E��	š��5����:P^�:����)R�^'��D,��z8�M�r P5�;�+\F8ö���,0�`\Nx��q�,�nԔ��w�9iquGw�t�[S��[ٯ������Z#�����I)?L�      �   �  x���j�@��㧘���.�T�@*;KCP�C�\�ɺX�~�n��Ra�l��s��/�s��[C]G�3�n��0�����ݽ~<�]��v	��>��Rڒ$��h���C��O0�v,A9���$�WR�'�����9E$���vo�d�pӿ9�/�#$�"R~�|� �s2��긍-�{,ԡ��~���C���i�n���
�m"7"TS亂�f��&=�<�|��b��S\
��[Yc��7�O�	�u|qE�Ѥ�$x�:)�0��H
��H�%��+�:��ͥ��eI!���ᣀ��B����߫9n���`�˨ �_����I^�jE���v���Ø���� q@�B�����n
ڰ���/�� ��y��� �st�@0(����N��3��ށӦ�?do�^�y�@��z�ު��E���ƿ�e�����D����'{\7��$=M����㘎>_�iF�/N��6S��@�-W�"-=#�����7���t: �<�      �   e  x��Yێ�Z}��_��.>rSAr�	AD�
�����>ű*��]	'>T�ec�9�k��h�+����-�%k�05������	�A/�RnǃY�e}<�D����H����NW��Uu����{�-	`J"T�Z��d�K40�'H�^"Rݠ��7j�U�����D���֛Ǣ
ױ��K�s�T�Nd�����\��;���B�γv��P�S���#��~�a��<̝�<ERH��ZƋȷl����q�\g���[�����I��b�:�t�xH���bw�E��j�@�U��q���Ʈ��g}����"��O��05��y<U3��ې-�nm�y/�r�S��Y�T�c�G��7$j#$�����&�W���O��xX,J%�_,?<A��\hH�V}2�ո!j/jz�uT%Y��<sh�������}0@^�Q`띹�D��7���(Y�;�������*g�����m3s�ҋL��	�/Y���-/K�yw�ٸH�@Y)gs#�A}�6.�I5��Y� �}��p0�"v�n�;�"�PĖ�B_��ҝx�U-+Q!a|�f��H*|A*vS%����7�S;ά�c�i��K�H�C��p�8����V����b���FD ���S������^��\]�#�,Z(+/���SQ�E���{~'��J�!�M�}�P�t�
�s���`/�5h��۞�jn�5
�|X_W�kwFV�!�Kvf�d�r]��S@�� ou�A,�Qk���D�\�V@��O�#�D��'�NZϞp}���;�o��z�Կ Qcj���pv1{��k�=�a�Ҷ�.��+�a��J0OϮ��R�3S����,��K�ڷ���ZiTݖ'm������!�z��J#�I���9�`�Їqu��DRG��1N���߱Ņҥ�)�,mTC����lh�re��{vm����*г�n�+,x��yc�^Hۭr�H<3m�wQ\���E)ua8q��n�����}9S�0��w?&A
���j���������H@�K�H!hI;��͑��E�;6K(z5
���a�яo�e���8K�d���J���#��ѐ����N��&��,'?�w��-�����&9��Ȱ,��nO�;�T��u��!�FH�*<&���Ä�ifc��u�me�h]��~x�q��E���g�y~�̮�
5[�VWN�8�ȏ���~_�p  �A"꠿lg7��ᕅ�O�YW7D����=a5}'qN�F��	�Y��UP��*�b�H����������Q'����sΏ��8Ckדv�g?h�W-���*�~_��n�Y���lPJ#�u��sUngZN�l��fI?l-��f�>�O��g1r��:w�J�/쇭ʹ@��e&��.:?K��|�>�^��q
]�M�|hƖ����}B�.0%�8l4���[��1�;�履�O�&��Om��
tlz{IM��	�W�V�~YݵF>y^�V�?8�n0���N|�rQ�d?m��T�Hm�</X3����8��3��mJ]]���_u­%���Ju�ߏk���|�'�� C=0�Q�UB��=�B�tCM��ٶe�w�"�'���Y\x/�B��t_�yy���1�7��� �b�D��0Spa�>U�I�$fw��/��
��t�yx,Z�e�*+�(irRR�Cz��+�t�%�l��r���y5'�`�8�N�p�e#%]�;+TJ��k�0=R����/B������<l[kZX$�8�-�|�����̻������Ź^�>��/�=��{K��\�����X����9�a�6�8�;�a�e�Ll����7�������0��(jt��6�{���PP^�-:�AR�=�>s�.��kI\�\}��o��g���g$h��P���R��#I���M�^F�cs��t�۠s]-�I�P���G޿�u���Ͻ.�Rm���A�X��]_+�m"5�<2
��R���0(�ٷ/"��_	�q�dρf�#\��R�e�Aԅk��H����J=�S�u1}h��'�d�gQ��2ݗ(��*'�߆�56�ܞ�0ݱvxً��P���)QF�Df_�C")��߉```��c�w�S����=�ϟ��˴��zq����_��`&�L���|F}ʥ��3�;,�m�����3��{�fŀl�ˢF(�moiF�o��!?F9�/��iʨ)|_�^�%�TV|��K����{���{V�I?�\�c'9}��U��U���+��`�׋��X��r�bk>����9�/��A��=��ڬ�l���Yn�/���h7��^����Z<GW�.�L�Q�{FD�.���X�� �x�n��{�����b"�I%�&���K%�	q��_����bD��0����<z�W|�����A������q9�вN�}�(:��;����Qx<�j�o� �'k0"��kk��<��NnZ�X�j���Гց�'�c�a��][��쳋�+�
c��ﯫ�"ħ�g/"���'JNB�z|q»�u�c�:~��N�5ݖ&��uZ}�i>1;r�4>n�������/�ࠆ���]�p�� �Sc�6w��k�P��P���ɀ�
�pjHF��I5�Y9_�8%[�{�^_���H�*A�m�S����N6��rv"�f����N$�=G"+.�lqvR�����G�:@*�0ITI�v]-����N�B6���3Ѡ� w��c��X��k/�[W
��`��ߟ�����L��ZLF.����;�x��>�)^��q����$�'3����X��Y҂���&]��\��d!I�9(�A��	}��`�|��-���d�(4�&y��^.6I�Rba��4��_?��7'Qϵ      �      x�3�4�2�4�2cS 6����� (�      �      x�3�4�2�4�2b ����� ��      �     x���]o�Pǯ���o��Z@�j5M
��K+P��%��ǲ&��e��7��e6n�4�r^�<���{D�Z��M�wϜ��(����ә��w���Y$a��H�(�f���� ��E��m��PϛR��[��4���5�z�^�]*����u�&�B�[�R�2y�
���!v$")X�x��4���3+��B�yL���G,���������f��Բ�,���1����
Ι�n�ǋ�j��*�@&�,�c�dκ�?֚�.�{9T�l��o�M��I�O��d*����}�>��^�M�$�%}�V��tC��P:A>y6K3$��*�7�x�uD;wL��:�cmZ
Zb_.7BcYv��e䪰5\.BKk��Qߺ��n�_h+�2"n�N@�㳃8~_�U��ɛ�	��%zp1gN����s4���g7vN䘞gc��eM~��nsJZ6�$��q�ñמ6��r�3ݘȑ�Rx�Z����bH	(n*�ȼ��1����/�/��+�,�C�
�x�P���     