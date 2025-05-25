import axios from "axios";
import NextAuth from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials";
import { SERVER_URI } from "./constants/constant";

export const { handlers, signIn, signOut, auth } = NextAuth({
    providers: [

        // Local Providers
        CredentialsProvider({
            name: "Credentials",
            credentials: {
                email: { label: "Email", type: "text" },
                password: { label: "Password", type: "password" },
            },

            async authorize(credentials) {
                if (!credentials?.email || !credentials?.password) {
                    throw new Error("Email and password are required");
                }

                try {
                    const response = await axios.post(`${SERVER_URI}/api/v1/auth/login`, {
                        email: credentials.email,
                        password: credentials.password,
                    }, {
                        headers: {
                            'Content-Type': 'application/json',
                        },
                    });

                    const data = response.data;

                    return {
                        id: data.id,
                        email: data.email,
                        accessToken: data.token,
                    };
                } catch (error) {
                    console.error("Credentials login error:", error);
                    throw new Error("Invalid credentials");
                }
            }
        })
    ],

    session: {
        strategy: "jwt" // Use JWT for session management
    },

    pages: {
        signIn: "/auth/login"
    },

    trustHost: true,

    callbacks: {
        async jwt({ token, user }) {
            // On initial login
            if (user) {
                token.id = user.id;
                token.email = user.email;
                token.accessToken = user.accessToken;
            }
            return token;
        },
        async session({ session, token }) {
            if (token) {
                session.user.id = token.id as string;
                session.user.email = token.email as string;
                session.accessToken = token.accessToken as string;
            }
            return session;
        }
    }
})