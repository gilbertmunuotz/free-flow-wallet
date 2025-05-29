/* eslint-disable @typescript-eslint/no-explicit-any */
"use client";

import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import { UpdateUser } from "@/app/settings/_action";
import { useRouter } from "next/navigation";

export default function UpdateForm({ user }: { user: { fullName: string; email: string } }) {

    const router = useRouter();

    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading(true);

        const formData = new FormData(e.currentTarget);

        try {
            await UpdateUser(formData)
            router.push("/settings")
            toast.success("User Updated Successfully")
        } catch (error: any) {
            toast.error(error.message || "User Update failed")
        }
        finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <div>
                <label className="block text-sm font-medium mb-1">Name</label>
                <Input
                    required
                    type="text"
                    name="fullName"
                    defaultValue={user.fullName}
                    placeholder="Enter your full name"
                />
            </div>

            <div>
                <label className="block text-sm font-medium mb-1">Email</label>
                <Input
                    required
                    name="email"
                    type="email"
                    defaultValue={user.email}
                    placeholder="Enter your email"
                />
            </div>

            <div>
                <label className="block text-sm font-medium mb-1">Password</label>
                <Input
                    required
                    type="password"
                    name="password"
                    placeholder="••••••••"
                />
            </div>
            <div>
                <label className="block text-sm font-medium mb-1">PIN</label>
                <Input
                    required
                    type="number"
                    name="pin"
                    placeholder="e.g 1234"
                />
            </div>
            {loading ? (
                <Button className="cursor-not-allowed" disabled>Updating...</Button>
            ) : (
                <Button type="submit" className="flex justify-right">Update</Button>
            )}
        </form>
    )
}