import { DefaultSession, DefaultUser } from "next-auth";

// Extend the `Session` type
declare module "next-auth" {
    interface Session {
        accessToken?: string;
        user: {
            id?: string;
        } & DefaultSession["user"];
    }

    interface User extends DefaultUser {
        id?: string;
        accessToken?: string;
    }
}

// Extend the `JWT` type
declare module "next-auth/jwt" {
    interface JWT {
        id?: string;
        accessToken?: string;
    }
}